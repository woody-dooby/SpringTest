package com.spring.Async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * Exception 발생 시 어떻게 될까?
 *  해당 Thread 만 죽고 전체 프로세스에는 영향이 없음. 만약, 관리가 필요하다면 Exception 처리를 진행할 수 있음.
 *
 * 추가적으로 WAS 종료시 threadPoolTaskExecutor가 정상적으로 종료되도록 destroyMethod을 설정이 필요.
 * why?
 *  WAS 프로세스가 종료되는 과정에서 threadPoolTaskExecutor bean을 종료해야하는데 그 방법을 약속하지 않았고 결국 종료에 실패해서 memory 에러 메시지가 발생한다.
 * 물론 threadPoolTaskExecutor 이 가지는 메모리에 대하여 반환이 되겠지만 불필요한 에러는 운영상에 안좋은 영향을 줄 수도 있기에 제거해야 하므로 destroyMethod 를 통하여 제거한다.
 * 왠만하면 Future 객체로 전달하면 좋다.
 * */
@Slf4j
@EnableAsync
@Configuration
public class AsyncTaskExceptionWrapperTest {

    @Async("threadPoolTaskExecutor")
    public Future<String> asyncMethod(){
        log.info("This is ThreadPoolTaskExecutorException");
        log.info("===============================");

        return new AsyncResult<>("TEST");
    }

    @Bean(value = "threadPoolTaskExecutor", destroyMethod = "close")
    public Executor threadPoolTaskExecutor(){
        log.info("threadPoolTaskExecutor : CREATE");

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("Executor-");
        taskExecutor.initialize();

        return new ExecuteHandlingWrapper(taskExecutor);
    }

    @RequiredArgsConstructor
    public class ExecuteHandlingWrapper implements AsyncTaskExecutor {
        private final AsyncTaskExecutor executor;

        @Override
        public void execute(Runnable task, long startTimeout) {
            executor.execute(createWrappedRunnable(task), startTimeout);
        }

        @Override
        public Future<?> submit(Runnable task) {
            return executor.submit(createWrappedRunnable(task));
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return executor.submit(createCallable(task));
        }

        @Override
        public void execute(Runnable task) {
            executor.execute(createWrappedRunnable(task));
        }
        //Callable exception
        private <T> Callable<T> createCallable(final Callable<T> task) {
            return () -> {
                try {
                    return task.call();
                } catch (Exception ex) {
                    handle(ex);
                    throw ex;
                }
            };
        }
        //Runnable exception handle
        private Runnable createWrappedRunnable(final Runnable task) {
            return () -> {
                try {
                    task.run();
                } catch (Exception ex) {
                    handle(ex);
                }
            };
        }
        //exception handle
        private void handle(Exception ex) {
            log.info("Failed to execute task. : {}", ex.getMessage());
            log.error("Failed to execute task. ",ex);
        }

        public void close() {
            if(executor instanceof ThreadPoolTaskExecutor){
                ((ThreadPoolTaskExecutor) executor).shutdown();
            }
        }

    }

}

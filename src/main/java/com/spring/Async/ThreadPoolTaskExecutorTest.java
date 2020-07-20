package com.spring.Async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
//@Configuration
//@EnableAsync : 테스트 시 주석 풀고, application(SpringTestApplication) 상단의 @EnableAsync 를 제거해주세요.
public class ThreadPoolTaskExecutorTest {
    //실행 시 @EnableAsync 설정을 설정하는 커스텀 클래스에 사용하며 application 클래스 상단의 @EnableAsync 를 제거해야함.
    //기존의 사용하는 SimpleAsyncTaskExecutor 를 Configuration 설정으로 만들어진 ThreadPoolTaskExecutor 로 대체한다.
    // ConditionalOnXXBeanConfig 에서 이미 설정됨.

    @Async("threadPoolTaskExecutor")
    public void asyncMethod(){
        log.info("This is ThreadPoolTaskExecutor");
        log.info("===============================");
    }

    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor(){
        log.info("threadPoolTaskExecutor : CREATE");

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("Executor-");
        taskExecutor.initialize();

        return taskExecutor;
    }


}

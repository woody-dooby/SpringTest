package com.spring.ioc.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@Slf4j
public class ConditionalOnXXBeanConfig {


    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor(){
        log.info("threadPoolTaskExecutor : CREATE");

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("Executor-");
        taskExecutor.initialize();

        return taskExecutor;

    }
    //생성된다.
    @Bean(value = {"customThreadPoolExecutor", AsyncAnnotationBeanPostProcessor.DEFAULT_TASK_EXECUTOR_BEAN_NAME})
    @ConditionalOnBean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor customThreadPoolExecutor(TaskExecutorBuilder builder){
        log.info("customThreadPoolExecutorBean : CREATE");
        return builder.build();
    }
    //생성되지 않을것이다. 왜냐하면 앞에서 이미 생성했으니깐..
    @Bean(value = "customMissingThreadPoolExecutor")
    @ConditionalOnMissingBean(name = "customThreadPoolExecutor")
    public ThreadPoolTaskExecutor customMissingThreadPoolExecutor(TaskExecutorBuilder builder){
        log.info("customThreadPoolExecutorMissingBean : CREATE");
        return builder.build();
    }

}

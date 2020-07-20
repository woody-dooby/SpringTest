package com.spring.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@Slf4j
public class ConditionalOnXXClassConfig {


    @Bean("classThreadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor(){
        log.info("classThreadPoolTaskExecutor : CREATE");

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("Executor-");
        taskExecutor.initialize();

        return taskExecutor;

    }

    @Bean(value = "customClassThreadPoolExecutor")
    @ConditionalOnClass(Executor.class)
    public ThreadPoolTaskExecutor customClassThreadPoolExecutor(TaskExecutorBuilder builder){
        log.info("customClassThreadPoolExecutor : CREATE");
        return builder.build();
    }
    //생성되지 않을것이다. 왜냐하면 앞에서 이미 생성했으니깐..
    @Bean(value = "customClassMissingThreadPoolExecutor")
    @ConditionalOnMissingClass(value = "org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor customClassMissingThreadPoolExecutor(TaskExecutorBuilder builder){
        log.info("customClassMissingThreadPoolExecutor : CREATE");
        return builder.build();
    }

}

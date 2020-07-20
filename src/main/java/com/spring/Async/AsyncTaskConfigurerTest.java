package com.spring.Async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/*
* AsyncConfigurer 를 구현하면 @Async 사용 시에 전체적을 적용할 수 있다.
*  - getAsyncExecutor() : TaskExecutor 를 사용.
*  - getAsyncUncaughtExceptionHandler() : error 에 대한 Handler 처리 이다.
* */
//@Configuration  //테스트 시 사용하세요.
public class AsyncTaskConfigurerTest implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncTaskExceptionHandlerTest();
    }
}

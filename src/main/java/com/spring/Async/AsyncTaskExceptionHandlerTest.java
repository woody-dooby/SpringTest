package com.spring.Async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class AsyncTaskExceptionHandlerTest implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error(ex.getMessage());
        log.debug("ERROR METHOD : {}",method.getName() );
        log.debug("METHOD PARAM ");
        Arrays.stream(params).map(Object::toString).forEach(log::debug);
    }
}

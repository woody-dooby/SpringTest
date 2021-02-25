package com.spring.Async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class AsyncTaskRunner implements ApplicationRunner {
    @Autowired
    AsyncTaskExceptionWrapperTest test;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        IntStream.range(0,100).forEach(t->test.asyncMethod());
    }
}

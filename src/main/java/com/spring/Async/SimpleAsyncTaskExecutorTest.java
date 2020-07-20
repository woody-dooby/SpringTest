package com.spring.Async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
/*
* 제약사항
* 1. pulbic method에만 사용가능 함.
* 2. 같은 객체내의 method끼리 호출시에는 @Async가 설정되어있어도 비동기처리가 되지 않음.
*
* */
@Slf4j
public class SimpleAsyncTaskExecutorTest {

    //실행 시 @EnableAsync 설정이 필요하며, SimpleAsyncTaskExecutor 를 사용하여 @Async 를 관리.
    //SimpleAsyncTaskExecutor 는 Thread 관리를 별도로 하고 있지 않다.
    @Async
    public void asyncMethod(){
      log.info("This is SimpleAsyncTaskExecutor");
      log.info("===============================");
    }
}

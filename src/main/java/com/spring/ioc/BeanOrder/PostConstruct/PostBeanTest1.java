package com.spring.ioc.BeanOrder.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class PostBeanTest1 {

    @Autowired
    PostBeanTest3 beanTest3;

    PostBeanTest1(){
        log.info(this.getClass().getSimpleName() + " TEST : HELLO");
    }

    @PostConstruct
    public void hello(){
        log.info(this.getClass().getSimpleName() + " Post TEST : HELLO");
        log.info("@@[PostConstruct Dependency TEST] : START@@");
        beanTest3.hello(); //생성자에 있을 때랑은 다르게 작동이 가능.
        log.info("@@[PostConstruct Dependency TEST] : END@@");
    }

}

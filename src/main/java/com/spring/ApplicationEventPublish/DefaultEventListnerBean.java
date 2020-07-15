package com.spring.ApplicationEventPublish;

import org.springframework.context.event.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DefaultEventListnerBean {
    @EventListener
    @Async
    public void handle(ContextRefreshedEvent event){
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextRefreshEvent");
    }

    @EventListener
    @Async
    public void handle(ContextStartedEvent event){
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextStartedEvent");
    }

    @EventListener
    @Async
    public void handle(ContextStoppedEvent event){
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextStoppedEvent");
    }

    @EventListener
    @Async
    public void handle(ContextClosedEvent event){
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextClosedEvent");
    }

}

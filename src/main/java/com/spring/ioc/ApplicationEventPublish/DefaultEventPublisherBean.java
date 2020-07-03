package com.spring.ioc.ApplicationEventPublish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DefaultEventPublisherBean implements ApplicationRunner {
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = ((ConfigurableApplicationContext)applicationContext);
//        configurableApplicationContext.start(); // ContextStartedEvent 를 발생
//        configurableApplicationContext.refresh(); // ContextRefreshedEvent 를 발생
//        configurableApplicationContext.stop();  // ContextStoppedEvent 를 발생
//        configurableApplicationContext.close(); // ContextClosedEvent 를 발생


    }
}

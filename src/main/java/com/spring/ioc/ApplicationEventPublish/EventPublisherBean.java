package com.spring.ioc.ApplicationEventPublish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class EventPublisherBean implements ApplicationRunner {
    @Autowired
    ApplicationContext applicationContext;

    //이벤트 생성이 모두 끝난 시기에 publish 해야 한다.
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String,String> map = Collections.singletonMap("data","ApplicationEvent Implement EventPublisher");
        //ApplicationEventPublish 가 제공.
        applicationContext.publishEvent( new ApplicationEvent1(map));    //ApplicationEvent 상속
        applicationContext.publishEvent( new ApplicationEvent2("object EventPublisher","object EventPublisher2"));    //일반 Object
    }
}

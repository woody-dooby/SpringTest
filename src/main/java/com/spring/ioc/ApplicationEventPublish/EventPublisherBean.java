package com.spring.ioc.ApplicationEventPublish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class EventPublisherBean implements ApplicationRunner {
    @Autowired
    ApplicationContext applicationContext;

    //이벤트 생성이 모두 끝난 시기에 publish 해야 한다.
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String,String> map = Collections.singletonMap("data","ApplicationEvent Implement EventPublisher");

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //ApplicationEventPublish 가 제공.
        service.execute(()->{
            System.out.println(Thread.currentThread().toString());
            while(true){
                //ApplicationEvent 상속
                service.execute(()->{
                    System.out.println(Thread.currentThread().toString());
                    applicationContext.publishEvent( new ApplicationEvent1(map));
                });
                //일반 Object
                service.execute(()->{
                    System.out.println(Thread.currentThread().toString());
                    applicationContext.publishEvent(  new ApplicationEvent2("object EventPublisher","object EventPublisher2"));
                } );
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}

package com.spring.ApplicationEventPublish;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            log.info("TEventPublisherBean TEST");
            log.info("ThreadName : {}",Thread.currentThread().toString());

            do {
                //ApplicationEvent 상속
                service.execute(() -> {
                    log.info("ThreadName : {}", Thread.currentThread().toString());
                    applicationContext.publishEvent(new ApplicationEvent1(map));
                });
                //일반 Object
                service.execute(() -> {
                    log.info("ThreadName : {}", Thread.currentThread().toString());
                    applicationContext.publishEvent(new ApplicationEvent2("object EventPublisher", "object EventPublisher2"));
                });
                try {
                    //5초간의 텀을 둠.
                    Thread.sleep(5 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (Boolean.parseBoolean(applicationContext.getEnvironment().getProperty("debug")));

        });

    }
}

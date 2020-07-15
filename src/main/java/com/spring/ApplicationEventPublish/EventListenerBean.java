package com.spring.ApplicationEventPublish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListenerBean {

    @EventListener
    public void handle(ApplicationEvent1 applicationEvent){
        log.info(applicationEvent.getData());
    }
    //이렇게 데이터 변경하여 넘겨줄 수 있음.
    @EventListener
    @Order(value = Ordered.HIGHEST_PRECEDENCE+1)
    public void handle(ApplicationEvent2 applicationEvent){
        log.info(applicationEvent.getData());
        applicationEvent.setData(applicationEvent.getData2());
    }
    @EventListener
    @Order(value = Ordered.HIGHEST_PRECEDENCE+2)
    public void handle2(ApplicationEvent2 applicationEvent){
        log.info(applicationEvent.getData());
    }
}

package com.spring.ioc.BeanOrder.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostBeanTest2 {

    PostBeanTest2(){
        hello();
    }
    public void hello(){
        log.info(this.getClass().getSimpleName()+ " TEST : HELLO");
    }
}

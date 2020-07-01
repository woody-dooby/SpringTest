package com.spring.ioc.BeanOrder.DependsOn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DependBeanTest2 {

    DependBeanTest2(){
        hello();
    }
    public void hello(){
        log.info(this.getClass().getSimpleName()+ " TEST : HELLO");
    }
}

package com.spring.ioc.BeanOrder.DependsOn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("DependBean3")
public class DependBeanTest3 {

    DependBeanTest3(){
        hello();
    }

    public void hello(){
        log.info(this.getClass().getSimpleName() + " TEST : HELLO");
    }
}

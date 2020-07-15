package com.spring.BeanOrder.Order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 3)
public class OrderBeanTest1 implements OrderBeanInterface {

    OrderBeanTest1(){
        hello();
    }
    @Override
    public void hello(){
        log.info(this.getClass().getSimpleName() + " TEST : HELLO");
    }

}

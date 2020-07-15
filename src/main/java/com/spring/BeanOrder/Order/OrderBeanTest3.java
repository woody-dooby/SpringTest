package com.spring.BeanOrder.Order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class OrderBeanTest3 implements OrderBeanInterface {

    OrderBeanTest3(){
        hello();
    }

    @Override
    public void hello(){
        log.info(this.getClass().getSimpleName() + " TEST : HELLO");
    }
}

package com.spring.ioc.BeanOrder.Order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 2)
public class OrderBeanTest2 implements OrderBeanInterface {

    OrderBeanTest2(){
        hello();
    }
    @Override
    public void hello(){
        log.info(this.getClass().getSimpleName()+ " TEST : HELLO");
    }
}

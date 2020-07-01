package com.spring.ioc.BeanOrder.Order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class OrderBeanTest0{

    @Autowired
    List<OrderBeanInterface> list;

    OrderBeanTest0(){
        hello();
    }

    public void hello(){
        log.info(this.getClass().getSimpleName() + " TEST : HELLO");
    }
    @PostConstruct
    public void order(){
        list.forEach(t->log.info(t.getClass().getSimpleName() + " Order Dependency TEST "));
    }

}

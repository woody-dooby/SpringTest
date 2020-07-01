package com.spring.ioc.BeanOrder.Order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Order : Bean의 생성 순서가 아닌! ,동일한 객체를 사용하는 Bean을 생성하거나 같은 인터페이스를 가지는 Bean 들의 의존성(생성이 아님!) 주입 시 Bean의 우선 순위 이다.!!
 */
@Slf4j
@Component
@Order(value = 3)
public class OrderBeanTest1 implements OrderBeanInterface{

    OrderBeanTest1(){
        hello();
    }
    @Override
    public void hello(){
        log.info(this.getClass().getSimpleName() + " TEST : HELLO");
    }

}

package com.spring.ioc.BeanOrder.DependsOn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 *  @DependsOn :  해당 Bean은 어떤 X라는 빈을 참조하고 있다 라는 것을 알려 줌. 결론적으로, 해당 빈보다 우선 순위로 생성됨.
 *              어느 글귀에서 해당 Annotation 은 사용자에게 혼란을 주며 급박한 상황이 아니라면 상황해서는 안되는 코드라고 설명하고 있었다.
 *              Bean 자체의 순서를 정하는 방법.
 */
@Slf4j
@Component
@DependsOn(value = "DependBean3")
public class DependBeanTest1 {

    @Autowired
    DependBeanTest3 beanTest3;

    DependBeanTest1(){
        hello();
//        beanTest3.hello(); //error발생      //빈이 생성되는 이후에 property 의존성이 주입이 되기에 안됨.
    }

    public void hello(){
        log.info(this.getClass().getSimpleName() + " TEST : HELLO");
    }

}

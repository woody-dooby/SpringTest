package com.spring.ioc.BeanDefine.BeanCycle.AnnotationCycle;

import com.spring.ioc.BeanDefine.BeanCycle.CycleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class AnnotationBeanTest {

    @Autowired
    @Qualifier(value = "cycleTest3")
    CycleBean bean;

    @PostConstruct
    public void hello(){
        bean.hello(); //생성자에 있을 때랑은 다르게 작동이 가능.
    }
    @PreDestroy
    public void destroy(){
        log.info( AnnotationBeanTest.class.getSimpleName()+" TEST : DESTROY");
    }

}

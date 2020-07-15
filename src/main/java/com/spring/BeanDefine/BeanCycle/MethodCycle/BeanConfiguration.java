package com.spring.BeanDefine.BeanCycle.MethodCycle;

import com.spring.BeanDefine.BeanCycle.CycleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean(name = "cycleTest1")
    public CycleBean cycleBean1(){
        return new CycleBean("InterfaceDefineBean");
    }
    @Bean(name = "cycleTest2", initMethod = "hello" , destroyMethod = "destroy")
    public CycleBean cycleBean2(){
        return new CycleBean("MethodDefineBean");
    }
    @Bean(name = "cycleTest3")
    public CycleBean cycleBean3(){
        return new CycleBean("AnnotationBeanTest");
    }
}

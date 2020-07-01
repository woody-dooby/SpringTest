package com.spring.ioc.BeanCycle.SpringAwareCycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component(value = "testerBean")
public class BeanNameAwareBean implements BeanNameAware {

    String name;
    @Override
    public void setBeanName(String s) {
        name = s;
        System.out.println(name.equals("testerBean"));
    }
}

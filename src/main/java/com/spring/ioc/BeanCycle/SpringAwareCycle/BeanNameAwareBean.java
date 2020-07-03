package com.spring.ioc.BeanCycle.SpringAwareCycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component(value = "testerBean")
@Slf4j
public class BeanNameAwareBean implements BeanNameAware {

    String name;
    @Override
    public void setBeanName(String s) {
        name = s;
        log.info("BeanNameAware TEST : IsName 'testerBean'? {}",name.equals("testerBean"));
    }
}

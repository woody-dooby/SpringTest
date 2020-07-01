package com.spring.ioc.Dependency.DependencyInjection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 3번째 SetterDependencyInjection
 */
@Component
@Slf4j
public class SetterDependencyInjection {
    @Autowired
    DIBean bean;

    public DIBean getBean() {
        return bean;
    }

    public void setBean(DIBean bean) {
        this.bean = bean;
    }
    @PostConstruct
    void setterDI(){
        log.info(SetterDependencyInjection.class.getSimpleName() + " TEST");
        bean.hello();
    }
}

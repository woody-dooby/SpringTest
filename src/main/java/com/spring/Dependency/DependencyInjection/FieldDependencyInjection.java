package com.spring.Dependency.DependencyInjection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 2번쨰 FieldDependencyInjection
 */
@Component
@Slf4j
public class FieldDependencyInjection {

    @Autowired
    DIBean bean;

    @PostConstruct
    void fieldDI(){
        log.info(FieldDependencyInjection.class.getSimpleName() + " TEST");
        bean.hello();
    }
}

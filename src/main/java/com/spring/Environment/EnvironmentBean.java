package com.spring.Environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnvironmentBean implements ApplicationContextAware {
    Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        environment = applicationContext.getEnvironment();
        log.info(String.join("-", environment.getActiveProfiles()));
        log.info(String.join("-",environment.getDefaultProfiles()));

    }
}

package com.spring.ioc.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "spring.custom", name = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(CustomPropertyConfigurationProperties.class)
public class ConditionalOnPropertyConfig {

    @Bean
    public String propertyBean(CustomPropertyConfigurationProperties property){
        log.info("ConditionalOnPropertyConfig TEST : {}",property.getName());
        return property.getName();
    }
}

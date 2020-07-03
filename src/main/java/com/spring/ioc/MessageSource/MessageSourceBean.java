package com.spring.ioc.MessageSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageSourceBean{

    @Autowired
    MessageSource messageSource;

    @PostConstruct
    void test(){
         String testMessage  =messageSource.getMessage("test",null, LocaleContextHolder.getLocale());
         log.info("MessageSourceBean : {}",testMessage);
    }
}

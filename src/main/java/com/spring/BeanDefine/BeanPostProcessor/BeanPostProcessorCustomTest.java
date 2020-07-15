package com.spring.BeanDefine.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BeanPostProcessorCustomTest implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //@PostConstruct
        if(beanName.equals("beanPostProcessorTestBean")){
            BeanPostProcessorTestBean customBean = (BeanPostProcessorTestBean)bean;
            customBean.setType("BeanPostProcessorCustomTest");
            customBean.setName("START");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //@PreDestroy
        if(beanName.equals("beanPostProcessorTestBean")){
            BeanPostProcessorTestBean customBean = (BeanPostProcessorTestBean)bean;
            //initializeBean 을 거치고 name 이 변경
            log.info("{} : {}",customBean.getType() ,customBean.getName());
        }
        return null;
    }
}



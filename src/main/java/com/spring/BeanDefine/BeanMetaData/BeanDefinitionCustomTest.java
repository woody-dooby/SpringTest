package com.spring.BeanDefine.BeanMetaData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class BeanDefinitionCustomTest implements ApplicationContextAware {

    @Autowired
    ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //AnnotationConfigApplicationContext 이겠지만.. -> 웹 환경에 따라 다름.
        // 모든 Application 이 GenericWebApplicationContext 을 상속받음.
        RootBeanDefinition beanDefinition  = new RootBeanDefinition(BeanDefinitionTestBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("BeanDefinitionTest");  //Type (순서대로 이기에)
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("ROOT TEST"); //Name (순서대로 이기에)
        ((GenericWebApplicationContext)applicationContext).registerBeanDefinition("newBeanTester", beanDefinition);
    }
    @PostConstruct
    public void beanDefinitionTester(){

        BeanDefinitionTestBean bean = (BeanDefinitionTestBean) ctx.getBean("newBeanTester");
        log.info(bean.getType() + ":"+bean.getName());
    }
}

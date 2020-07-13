package com.spring.ioc.BeanDefine.BeanCycle.SpringAwareCycle;


import com.spring.ioc.Dependency.DependencyInjection.DIBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAwareBean implements ApplicationContextAware {

    ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
        DIBean bean = ctx.getBean(DIBean.class);
        bean.hello();
    }
}

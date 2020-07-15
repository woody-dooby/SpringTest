package com.spring.BeanDefine.BeanCycle.interfaceCycle;

import com.spring.BeanDefine.BeanCycle.CycleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InterfaceDefineBean implements InitializingBean, DisposableBean {
    @Autowired
    ApplicationContext context;

    @Override
    public void afterPropertiesSet() throws Exception {
        CycleBean bean = (CycleBean) context.getBean("cycleTest1");
        bean.hello();
    }

    @Override
    public void destroy() throws Exception {
        log.info(InterfaceDefineBean.class.getSimpleName() +" TEST DESTROY");
    }
}

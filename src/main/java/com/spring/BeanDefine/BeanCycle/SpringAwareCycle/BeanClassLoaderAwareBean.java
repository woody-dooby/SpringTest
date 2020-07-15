package com.spring.BeanDefine.BeanCycle.SpringAwareCycle;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Component;

/**
 * 무슨 용도인가요?
 */
@Component
public class BeanClassLoaderAwareBean implements BeanClassLoaderAware {

    String name;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("I'DONT KNOW");
    }
}

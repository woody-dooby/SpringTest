package com.spring.Dependency.DependencyLookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * DependencyPool(DL) 방식 : IoC 컨테이너가 관리중인 객체 저장소(Pool)에서 객체를 검색하여 참조하는 방법. 컨테이너가 제공하는 api를 이용하여 Bean을 Lookup 하는 방식..
 *  - 그 중 하나인 Dependency pool(DP) 방식이다.
 *  - 나머지 Contextualized Dependency Lookup(CDL) 이 있으나 SpringBoot 에서 제공하지 않는것인지 못찾는 것인지 모르겠으나.. 컨테이너 레지스터가 아닌 Bean을 관리하는 별도의 컨테이너를 대상으로 Lookup 수행이라는 점만 알고있자..
 *     - FYI : CDL 예제를 찾았는데 해당 사항을 직접 만들어 container 처럼 사용하는 경우였다.
 */
@Component
public class DependencyPool implements ApplicationContextAware {

    //Spring 에서 사용되는 ApplicationContext 를 가져오기.
    private static ApplicationContext ctx;

    public static BeanFactory getBeanFactory(){
        //의존성 주입이 가능한 BeanFactory;
        return ctx.getAutowireCapableBeanFactory();
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @PostConstruct
    public void dependencyLookup() {

        //BeanFactory 가져오지만 사실상 BeanFactory 를 상속받아 만들어진 ApplicationContext 로 사용할 수도 있다.
        BeanFactory factory = getBeanFactory();
        DLBean bean = factory.getBean(DLBean.class);

        bean.hello();
    }

}

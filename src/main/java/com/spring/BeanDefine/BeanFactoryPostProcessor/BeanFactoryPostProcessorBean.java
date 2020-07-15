package com.spring.BeanDefine.BeanFactoryPostProcessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BeanFactoryPostProcessorBean implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //@Bean 과 @Component 계열 의 주는 빈 정의 정보가 다르다.
        //// @Bean - resource 가 AppClassLoader 이며, 해당 class 의 정보가 없다 -> 아마 reflect 을 이용하기에 그런거 같다. , 생성된 beanDefinition - RootBeanDefinition
        //// @Component - resource 가 FileClassLoader 이며, 해당 class 정보가 있다. , 생성된 beanDefinition - GenericBeanDefinition
        //MethodCycle 에서 생성한 Bean 이 메타정보로 등록되었다면 해당 빈 정보를 가져올 수 있을것이다.( @Bean 을 통한 등록)
        BeanDefinition annotationBeanDefinition =  configurableListableBeanFactory.getBeanDefinition("cycleTest1");
        //BeanOrder - DependsOn 에서 생성한 Bean 이 메타정보로 등록되었다면 해당 빈 정보를 가져올 수 있을것이다.(@Component 을 통한 등록 -> class 자체를 Bean 등록)
        BeanDefinition componentBeanDefinition =  configurableListableBeanFactory.getBeanDefinition("dependBeanTest1");

        //CycleBean 의 클래스 이름 정보를 확인.
        String beanClassName = annotationBeanDefinition.getBeanClassName();
        log.info( "BeanFactoryPostProcessorBean TEST @Bean - Name : {}", beanClassName);
        String componentBeanClassName = componentBeanDefinition.getBeanClassName();
        log.info( "BeanFactoryPostProcessorBean TEST @Component - Name : {}", componentBeanClassName);
    }
}

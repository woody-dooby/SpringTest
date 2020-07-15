package com.spring.BeanDefine.BeanPostProcessor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Data
@Slf4j
@Component
public class BeanPostProcessorTestBean implements InitializingBean {
    private String type;
    private String name;

    //BeanPostProcessor 의 순서가 과연 @PostConstruct 인지 확인하기 위하여.
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("{} : {}",this.type ,this.name);
        this.setName("END");
    }
}

package com.spring.ioc.BeanDefine.BeanMetaData;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class BeanDefinitionTestBean {
    private final String type;
    private final String name;

}

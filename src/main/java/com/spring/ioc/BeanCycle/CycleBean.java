package com.spring.ioc.BeanCycle;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CycleBean {
    private final String name;

    public void hello() {
        log.info( name+" TEST : HELLO");
    }
    public void destroy(){
        log.info( name+" TEST : DESTROY");
    }
}

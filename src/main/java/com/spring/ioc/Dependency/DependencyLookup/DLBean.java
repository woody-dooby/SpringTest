package com.spring.ioc.Dependency.DependencyLookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DLBean {

    DLBean(){
        hello();
    }
    public void hello(){
      log.info(this.getClass().getSimpleName() + " : DL TEST");
    }
}

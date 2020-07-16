package com.spring.Dependency.DependencyInjection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DIBean {

    public DIBean(){
        hello();
    }
    public void hello(){
      log.info(this.getClass().getSimpleName() + " : DI TEST ");
    }
}

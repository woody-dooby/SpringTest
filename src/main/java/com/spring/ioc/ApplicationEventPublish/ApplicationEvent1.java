package com.spring.ioc.ApplicationEventPublish;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

@Getter
public class ApplicationEvent1 extends ApplicationEvent {
    private final String data ;

    public ApplicationEvent1(Object source) {
        super(source);

        if( source instanceof Map){
            if(((Map) source).containsKey("data")){
                data = (String) ((Map) source).get("data");
            }else{
            data = "not";
            };
        }else{
            data = "not";
        }
    }
}

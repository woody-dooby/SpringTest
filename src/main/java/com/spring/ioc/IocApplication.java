package com.spring.ioc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(IocApplication.class)
                .web(WebApplicationType.SERVLET)
                .properties()
                .build().run(args);
        // == (깉디)
        //SpringApplication.run(IocApplication.class, args);
    }
}

package com.spring.ioc;

import com.spring.ioc.Configuration.CustomPropertyApplicationEnvironmentPreparedEvents;
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
                .listeners(new CustomPropertyApplicationEnvironmentPreparedEvents())
                .build().run(args);
        // == (깉디)
        //SpringApplication.run(IocApplication.class, args);
    }
}

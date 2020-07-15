package com.spring;

import com.spring.Configuration.CustomPropertyApplicationEnvironmentPreparedEvents;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringTestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringTestApplication.class)
                .web(WebApplicationType.SERVLET)
                .listeners(new CustomPropertyApplicationEnvironmentPreparedEvents())
                .build().run(args);
        // == (깉디)
        //SpringApplication.run(IocApplication.class, args);
    }
}

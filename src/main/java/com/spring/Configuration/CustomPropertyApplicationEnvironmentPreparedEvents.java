package com.spring.Configuration;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * springApplication 시작 시 등록 한다.
 */
@Component
public class CustomPropertyApplicationEnvironmentPreparedEvents  implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {

        Resource customResource = new ClassPathResource("application_custom.yaml");

        try {
            PropertySource propertySource = new YamlPropertySourceLoader().load("application_custom.yaml",customResource).get(0);
            applicationEnvironmentPreparedEvent.getEnvironment().getPropertySources().addLast(propertySource);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

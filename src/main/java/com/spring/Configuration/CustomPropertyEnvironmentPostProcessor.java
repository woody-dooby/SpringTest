package com.spring.Configuration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * META-INF/spring.factories 에 등록
 */
public final class CustomPropertyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        try {
            Resource customResource = new ClassPathResource("application_postProcessor.yaml");
            PropertySource propertySource = new YamlPropertySourceLoader().load("application_postProcessor.yaml",customResource).get(0);
            environment.getPropertySources().addLast(propertySource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

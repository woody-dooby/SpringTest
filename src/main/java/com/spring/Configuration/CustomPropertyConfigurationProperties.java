package com.spring.Configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.custom")
public class CustomPropertyConfigurationProperties {
    private String name;
    private boolean enabled;
}
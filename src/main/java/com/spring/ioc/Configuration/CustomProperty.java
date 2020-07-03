package com.spring.ioc.Configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.custom")
public class CustomProperty {
    private String name;
    private boolean enabled;
}
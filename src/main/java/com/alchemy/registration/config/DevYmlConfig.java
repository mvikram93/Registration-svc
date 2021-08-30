package com.alchemy.registration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:application-dev.yml", factory = YamlPropertySourceFactory.class)
public class DevYmlConfig {
    @Value("${foo}")
    private String foo;

    public String getValue() {
        return foo;
    }

    public void setValue(String foo) {
        this.foo = foo;
    }
}

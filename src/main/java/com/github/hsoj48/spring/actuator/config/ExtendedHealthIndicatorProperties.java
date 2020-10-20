package com.github.hsoj48.spring.actuator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "management.health")
public class ExtendedHealthIndicatorProperties {

    private final Map<String, ServiceConfiguration> indicators = new HashMap<>();

    @Data
    public static class ServiceConfiguration {

        private boolean enabled = true;
        private String url;
        private Auth auth;

    }

    @Data
    public static class Auth {

        private BasicAuth basic;

    }

    @Data
    public static class BasicAuth {

        private String username;
        private String password;

    }

}

package com.github.hsoj48.spring.actuator.config;

import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ConditionalOnClass(RestTemplate.class)
@EnableConfigurationProperties(ExtendedHealthIndicatorProperties.class)
public class PropertyBasedHealthAutoConfiguration {

    @Bean
    public HealthContributor propertyBasedHealthHealthContributor(ExtendedHealthIndicatorProperties props, RestTemplate restTemplate) {
        Map<String, HealthIndicator> indicators = new LinkedHashMap<>();

        for (Map.Entry<String, ExtendedHealthIndicatorProperties.ServiceConfiguration> entry : props.getIndicators().entrySet()) {
            if (entry.getValue().isEnabled()) {
                indicators.put(entry.getKey(), new StandardHttpHealthIndicator(restTemplate, entry.getKey(), entry.getValue()));
            }
        }

        return CompositeHealthContributor.fromMap(indicators);
    }

}

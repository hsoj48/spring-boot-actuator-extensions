package com.github.hsoj48.spring.actuator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class StandardHttpHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate;
    private final String healthCheckName;
    private final ExtendedHealthIndicatorProperties.ServiceConfiguration configuration;

    public StandardHttpHealthIndicator(RestTemplate restTemplate, String healthCheckName, ExtendedHealthIndicatorProperties.ServiceConfiguration configuration) {
        this.restTemplate = restTemplate;
        this.healthCheckName = healthCheckName;
        this.configuration = configuration;
    }

    @Override
    public Health health() {
        long startTime = System.currentTimeMillis();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(configuration.getUrl(), Object.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return Health.up().withDetail("responseTime", getRunningTime(startTime)).build();
            } else {
                return Health.down().withDetail("responseTime", getRunningTime(startTime)).build();
            }
        } catch (RestClientException e) {
            log.error("Health check [{}] failed due to a client error", healthCheckName, e);

            return Health.down()
                    .withException(e)
                    .build();
        }
    }

    private String getRunningTime(long startMillis) {
        long endTime = System.currentTimeMillis();
        return (endTime - startMillis) + "ms";
    }

}

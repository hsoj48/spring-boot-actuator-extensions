# Spring Boot Actuator Extensions

Provides common classes for configuration of the spring-boot-actuator project

## Maven

```xml
<dependencies>
    <dependency>
        <groupId>com.github.hsoj48</groupId>
        <artifactId>spring-boot-actuator-extensions</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Usage

### Http Health Auto Configuration

The class HealthIndicatorRegistryAutoConfiguration provides generic HealthIndicators for the Spring Health Actuator using only properties.  This is helpful for when a depedency exists on another external service that has a health endpoint.  Each configuration adds a health check that verifies that the supplied URL returns a 200.

#### Usage

Add a property for your service url.  The "service-name" will be used to name the health check and can be set to any value.
```
management.health.indicators.service-name.url=http://external-service.com/health
```

#### Available Configuration Properties

- management.health.indicators.service-name.enabled
  - Defaults to true, set to false to disable this health check
- management.health.indicators.service-name.url
  - Service URL for the health check
- management.health.indicators.service-name.auth.basic.username
  - Username for basic authentication
- management.health.indicators.service-name.auth.basic.password
  - Password for basic authentication
- management.health.indicators.service-name.auth.bearer.token
  - Token for bearer authentication

## Authors

 - Josh Fields
package org.screwdriver.tracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.screwdriver.tracker.service.TimeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"org.screwdriver.tracker.service", "org.screwdriver.tracker.controller", "org.screwdriver.tracker.authorization"})
@PropertySource("classpath:screwdriver.properties")
public class ApplicationConfig {

    @Value("${tracker.macSecret}")
    private String macSecret;

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() { return new ObjectMapper(); }

    @Bean
    public TimeService timeService() { return new TimeService(); }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}

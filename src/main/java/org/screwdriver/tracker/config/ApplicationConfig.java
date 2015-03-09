package org.screwdriver.tracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.screwdriver.tracker.service.TimeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.screwdriver.tracker")
public class ApplicationConfig {

    @Value("${token.secret}")
    private String tokenSecret;

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() { return new ObjectMapper(); }

    @Bean
    public TimeService timeService() { return new TimeService(); }


}

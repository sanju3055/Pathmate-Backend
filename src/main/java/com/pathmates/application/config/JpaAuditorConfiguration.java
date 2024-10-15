package com.pathmates.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class JpaAuditorConfiguration {

    @Bean
    public AuditorAware<String> getJpAuditorAware() {
        return new JpaAuditorAware();
    }

}

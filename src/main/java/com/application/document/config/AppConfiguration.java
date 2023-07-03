package com.application.document.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Created by Semih, 3.07.2023
 */
@Configuration
@EnableRetry
public class AppConfiguration extends CachingConfigurerSupport {

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:Messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}


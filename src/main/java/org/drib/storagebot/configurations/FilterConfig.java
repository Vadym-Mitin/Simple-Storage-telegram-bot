package org.drib.storagebot.configurations;

import org.drib.storagebot.security.RequestLoggingFilter;
import org.drib.storagebot.security.SecretTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FilterConfig {

    @Bean
    @Profile("test")
    public FilterRegistrationBean<SecretTokenFilter> secretTokenFilterRegister(SecretTokenFilter secretTokenFilter) {
        FilterRegistrationBean<SecretTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(secretTokenFilter);
        registrationBean.addUrlPatterns("/webhook/*"); // Apply filter only to /webhook/**
        registrationBean.setOrder(1); // Set order of execution if multiple filters exist
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilterRegister(RequestLoggingFilter filter) {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*"); // Apply filter only to /webhook/**
//        registrationBean.setOrder(1); // Set order of execution if multiple filters exist
        return registrationBean;
    }

}

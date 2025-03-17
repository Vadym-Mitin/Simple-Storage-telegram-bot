package org.drib.storagebot.configurations;

import org.drib.storagebot.security.SecretTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SecretTokenFilter> secretTokenFilterRegister(SecretTokenFilter secretTokenFilter) {
        FilterRegistrationBean<SecretTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(secretTokenFilter);
        registrationBean.addUrlPatterns("/webhook/*"); // Apply filter only to /webhook/**
        registrationBean.setOrder(1); // Set order of execution if multiple filters exist
        return registrationBean;
    }

}

package com.expensetracker.Configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomRequestFilter> customRequestFilterRegistration() {
        FilterRegistrationBean<CustomRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomRequestFilter());
        registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs
        registrationBean.setName("CustomRequestFilter");
        registrationBean.setOrder(1); // Set the order to ensure it runs before other filters
        return registrationBean;
    }
}


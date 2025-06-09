package com.suryacode.suryaapi.WebConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import com.suryacode.suryaapi.Interceptor.HeaderValidationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private HeaderValidationInterceptor headerValidationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerValidationInterceptor)
                .addPathPatterns("/api/**"); // Apply only to API endpoints
    }
}

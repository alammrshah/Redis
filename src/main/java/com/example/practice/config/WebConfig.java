package com.example.practice.config;

import org.springframework.context.annotation.Configuration;

import com.example.practice.Utility.Interceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/api/**"); // Apply to specific paths
    }
}
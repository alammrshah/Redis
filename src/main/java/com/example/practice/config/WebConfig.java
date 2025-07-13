package com.example.practice.config;

import com.example.practice.Utility.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.example.practice")
public class WebConfig implements WebMvcConfigurer {

  /**
   * Declare Interceptor as a Spring bean This ensures the interceptor is managed by Spring's IoC
   * container and can benefit from dependency injection if needed
   */
  @Bean
  public Interceptor customInterceptor() {
    return new Interceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // Register the interceptor bean instead of creating a new instance
    registry
        .addInterceptor(customInterceptor())
        .addPathPatterns("/api/**"); // Apply to specific paths
  }
}

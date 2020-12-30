package com.example.webdemo;

import com.example.webdemo.config.HeaderArgumentResolver;
import com.example.webdemo.intercept.ExceptionInterceptor;
import com.example.webdemo.intercept.HeaderInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    
    @Resource
    HeaderInterceptor headerInterceptor;
    
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerInterceptor);
        registry.addInterceptor(new ExceptionInterceptor());
    }

    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HeaderArgumentResolver());
    }
}

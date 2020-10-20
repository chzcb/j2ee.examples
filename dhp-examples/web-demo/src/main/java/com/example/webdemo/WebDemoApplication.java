package com.example.webdemo;

import com.example.webdemo.intercept.HeaderInterceptor;
import org.dhp.core.spring.DhpRpcClientScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@DhpRpcClientScanner(basePackages={"org.dhp.examples.rpcdemo","org.chzcb"})
public class WebDemoApplication {
    
    @Bean
    public HeaderInterceptor headerInterceptor(){
        return new HeaderInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }

}

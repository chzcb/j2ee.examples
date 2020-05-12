package com.example.webdemo;

import com.example.webdemo.intercept.HeaderInterceptor;
import com.example.webdemo.pojo.Header;
import org.dhp.core.spring.DhpProperties;
import org.dhp.core.spring.EnableDhpRpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(DhpProperties.class)
@EnableDhpRpcClient(basePackages="org.dhp.examples.rpcdemo")
public class WebDemoApplication {
    
    @Bean
    public HeaderInterceptor headerInterceptor(){
        return new HeaderInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }

}

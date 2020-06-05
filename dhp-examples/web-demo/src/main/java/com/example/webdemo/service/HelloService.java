package com.example.webdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class HelloService {
    
    int count;
    
    public void scheduleTask(){
        log.info("schedule task: {}", count++);
    }
    
    @Scheduled(fixedRate = 10000)
    public void springSchedule(){
        log.info("springscheduletask:{}",new Date());
    }
}

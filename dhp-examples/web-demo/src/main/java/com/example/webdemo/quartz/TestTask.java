package com.example.webdemo.quartz;

import com.example.webdemo.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@DisallowConcurrentExecution
public class TestTask extends QuartzJobBean {
    
    @Resource
    HelloService service;
    
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("test task runing");
        service.scheduleTask();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}

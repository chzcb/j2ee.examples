package com.example.webdemo.controller;

import com.example.webdemo.pojo.Header;
import com.example.webdemo.quartz.TestTask;
import lombok.extern.slf4j.Slf4j;
import org.dhp.examples.rpcdemo.pojo.HelloRequest;
import org.dhp.examples.rpcdemo.pojo.HelloResponse;
import org.dhp.examples.rpcdemo.service.IHelloService;
import org.quartz.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequestMapping("/hello")
@RestController
public class HelloController {

    @Resource
    IHelloService service;

    @RequestMapping("/say")
    public String say() {
//        if(service == null){
//            throw new Error("ddd");
//        }
        HelloRequest request = new HelloRequest();
        HelloResponse response = service.say(request);
        return response.getContent();
    }
    
    @RequestMapping("/say3")
    public String say3(
            @RequestParam(value = "words", required = true)
            String words,
            @RequestParam(value = "count", required = true)
            Integer count
    ) {
        return "hello "+words+",count:"+count;
    }
    
    @RequestMapping("/say2")
    public String say2(Header header) {
        log.info("request from {}", header.getIp());
        HelloRequest request = new HelloRequest();
        HelloResponse response = service.say(request);
        return response.getContent();
    }
    
    @Resource
    Scheduler quartzScheduler;
    
    @RequestMapping("/task/add")
    public String addTask() throws SchedulerException {
        long startAtTime = System.currentTimeMillis() + 1000 * 3;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = TestTask.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TestTask.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group)
                .startAt(new Date(startAtTime)).build();
        //将触发器与任务绑定到调度器内
        quartzScheduler.scheduleJob(jobDetail, trigger);
        return "ok";
    }
    
    @RequestMapping("/task/add2")
    public String addTask2() throws SchedulerException {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = TestTask.class.getName();
    
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TestTask.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        quartzScheduler.scheduleJob(jobDetail, trigger);
        return "ok";
    }
}

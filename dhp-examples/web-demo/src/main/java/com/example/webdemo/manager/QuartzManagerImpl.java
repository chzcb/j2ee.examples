package com.example.webdemo.manager;

import com.example.webdemo.pojo.JobEntity;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
 
/**
 * 定时任务管理
 * @author EDZ
 * @date 2019/3/2913:51
 */
@Component
public class QuartzManagerImpl {
    
    @Resource
    private SchedulerFactoryBean factory;
    
    /**
     * jobEntity
     *
     * @param jobEntity 任务实体
     * @param job       执行任务
     * @param dataMap   保存的参数
     * @throws SchedulerException
     */
    public void addSimpleJob(JobEntity jobEntity, Class<? extends Job> job, JobDataMap dataMap) throws SchedulerException {
        Scheduler schd = factory.getScheduler();
        //多少秒之后执行
        Date startTime = new Date(System.currentTimeMillis() + jobEntity.getDelaySecond() * 1000);
        dataMap.put("jobName", jobEntity.getName());
        dataMap.put("jobGroup", jobEntity.getJobGroup());
        JobDetail jobDetail = JobBuilder.newJob(job)
                .withIdentity(jobEntity.getName(), jobEntity.getJobGroup())
                .setJobData(dataMap)
                .build();
    
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity(TriggerKey.triggerKey("trigger" + jobEntity.getName(), "trigger" + jobEntity.getJobGroup()))
                .startAt(startTime)
                .build();
        //两者组成一个计划任务注册到scheduler
        if (jobEntity.getStatus() != 0) {
            schd.scheduleJob(jobDetail, trigger);
        }
    }
    
    /**
     * @throws
     * @title addDateJob
     * @description 添加到期执行的任务
     * @author haifeng.lv
     * @param: jobEntity 实体类
     * @param: job 执行类
     * @param: dataMap 数据
     * @updateTime 2019/5/14 19:30
     */
    public void addDateJob(JobEntity jobEntity, Class<? extends Job> job, JobDataMap dataMap) throws SchedulerException {
        Scheduler schd = factory.getScheduler();
        // 名称
        dataMap.put("jobName", jobEntity.getName());
        // 工作组
        dataMap.put("jobGroup", jobEntity.getJobGroup());
        // 工作详情
        JobDetail jobDetail = JobBuilder.newJob(job)
                .withIdentity(jobEntity.getName(), jobEntity.getJobGroup())
                .setJobData(dataMap)
                .build();
    
        // 设置执行策略
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity(TriggerKey.triggerKey("trigger" + jobEntity.getName(), "trigger" + jobEntity.getJobGroup()))
                // 设置开始时间
                .startAt((Date) dataMap.get("endDate"))
                .build();
    
        //两者组成一个计划任务注册到scheduler
        if (jobEntity.getStatus() != 0) {
            schd.scheduleJob(jobDetail, trigger);
        }
    }
    
    /**
     * 移除任务
     *
     * @param jobEntity
     */
    public void subJob(JobEntity jobEntity) {
        Scheduler schd = factory.getScheduler();
        try {
            schd.deleteJob(JobKey.jobKey(jobEntity.getName(), jobEntity.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
    public void subJob(String name, String group) {
        Scheduler schd = factory.getScheduler();
        try {
            schd.deleteJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 根据分组删除定时任务
     *
     * @param group
     */
    public void subJobByGroup(String group) {
        try {
            Scheduler schd = factory.getScheduler();
            GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(group);
            Set<JobKey> jobkeySet = schd.getJobKeys(matcher);
            List<JobKey> jobkeyList = new ArrayList<JobKey>();
            jobkeyList.addAll(jobkeySet);
            schd.deleteJobs(jobkeyList);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
    public void addCronJob(JobEntity jobEntity, Class<? extends Job> job, JobDataMap dataMap) throws SchedulerException {
        Scheduler schd = factory.getScheduler();
        //在初始化调度的时候clean一下
        schd.clear();
        dataMap.put("jobName", jobEntity.getName());
        dataMap.put("jobGroup", jobEntity.getJobGroup());
        JobDetail jobDetail = JobBuilder.newJob(job)
                .withIdentity(jobEntity.getName(), jobEntity.getJobGroup())
                .setJobData(dataMap)
                .build();
        
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobEntity.getName(), jobEntity.getJobGroup())
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(jobEntity.getCron())
                                //失效后启动不执行错过任务
                                .withMisfireHandlingInstructionDoNothing()
                ).startNow().build();
        
        jobEntity.setParameter(dataMap.toString());
        // jobEntityMapper.insert(jobEntity);
        //两者组成一个计划任务注册到scheduler
        if (jobEntity.getStatus() != 0) {
            schd.scheduleJob(jobDetail, trigger);
        }
//        if(!schd.isShutdown()){
//            schd.start();//启动调度器
//        }
    }
    
    public String getCorn() {
        return null;
    }
    
    
}
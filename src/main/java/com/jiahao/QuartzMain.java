package com.jiahao;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 任务启动类
 */
public class QuartzMain {

    public static void main(String[] args) throws SchedulerException {
        //1.创建job对象：你想要做什么?
        JobDetail jobDetail = JobBuilder.newJob(QuartzDemo.class).build();

        //2.创建tigger对象：什么时候做？
        /*
            简单Tigger时间触发器：通过Quartz提供的简单方法完成简单的调用
            SimpleScheduleBuilder.repeatSecondlyForever(2):表示每2s执行一次

            cron tigger:按照cron表达式来给定触发器
         */
        // 简单Tigger时间触发器：通过Quartz提供的简单方法完成简单的调用
//        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2)).build();

        //cron tigger:按照cron表达式来给定触发器

        /**
         * cron表达式：6位和7位
         * 6位：* * * * * *  分别代表（秒，分，时，日，月，星期）
         * 取值：
         * 秒：0-59
         * 分：0-59
         * 时：0-23
         * 日：1-30(31)
         * 月：1-12
         * 星期：1-7(注意点：星期天为第一天)
         *
         * ？表示占位符：如其中星期和日有冲突，就会舍弃其中一个，会用占位符
         * 0 * * 20 2 3:表示2月20号星期三，其中20号不一定为星期三，所有对星期或者日用？表示 0 * * 20 2 ?或者0 * * ? 2 3
         * 例如：0 * * * * ? 每1分钟触发一次
         * 0 0 * * * ? 每天每1小时触发一次
         * 0 0 10 * * ? 每天10点触发一次
         * 0 * 14 * * ? 在每天下午2点到下午2:59期间的每1分钟触发
         * 0 30 9 1 * ? 每月1号上午9点半
         * 0 15 10 15 * ? 每月15日上午10:15触发
         * 0 15,30 10 15 * ? 每月15日上午10:15和10::30各自触发一次
         * 0 0 5-15 * * ? 每天5-15点整点触发
         * 0 0/3 * * * ? 每三分钟触发一次
         * 0 0 0 1 * ?  每月1号凌晨执行一次
         */
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();


        //3.创建Scheduler对象：什么时候做什么事?
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail,trigger);

        //4.启动
        scheduler.start();
    }
}

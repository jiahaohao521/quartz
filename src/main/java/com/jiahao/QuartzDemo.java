package com.jiahao;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 定时任务类
 */
public class QuartzDemo implements Job {

    /**
     * 定时方法
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务执行时间..." + new Date());
    }
}

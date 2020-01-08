package com.zbcn.combootjob.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloWorldJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("欢迎使用定时任务,这是一个定时任务 hello word!"+ new Date().toInstant());
	}
}

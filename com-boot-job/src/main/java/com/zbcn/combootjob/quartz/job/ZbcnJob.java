package com.zbcn.combootjob.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ZbcnJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("定时任务2--zbcn" + context.getJobDetail());
	}
}

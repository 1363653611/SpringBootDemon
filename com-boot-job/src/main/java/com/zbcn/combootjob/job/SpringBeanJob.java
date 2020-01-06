package com.zbcn.combootjob.job;

import com.zbcn.combootjob.service.impl.TestServiceImpl;
import com.zbcn.combootjob.utils.SpringContextUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SpringBeanJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		TestServiceImpl testServiceImpl = (TestServiceImpl)SpringContextUtils.getBean("testServiceImpl");
		testServiceImpl.doSomeThing();
	}
}

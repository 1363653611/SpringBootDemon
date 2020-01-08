package com.zbcn.combootjob.schedule.job.impl;

import com.zbcn.combootjob.schedule.job.IJob;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestJob implements IJob {
	@Override
	public void doJob() {
		System.out.println("测试任务:"+ LocalDateTime.now()+","+Thread.currentThread().getName());
	}
}

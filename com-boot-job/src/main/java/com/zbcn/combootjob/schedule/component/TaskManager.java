package com.zbcn.combootjob.schedule.component;

import com.zbcn.combootjob.schedule.job.IJob;
import com.zbcn.combootjob.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TaskManager {
	public static final Logger log = LoggerFactory.getLogger(TaskManager.class);

	@Autowired
	private TaskConfiguration taskConfiguration;

	private ExecutorService executorService = Executors.newSingleThreadExecutor();

	/**
	 * 添加定时任务
	 * @param taskId
	 * @param taskName
	 * @param corn
	 */
	public void addTask(String taskId, String taskName,String corn){
		TriggerTask triggerTask = new TriggerTask(new Task(taskName), new CronTrigger(corn));
		taskConfiguration.addTriggerTask(taskId,triggerTask);
	}

	/**
	 * 删除Task
	 * @param taskId
	 */
	public void removeTask(String taskId){
		taskConfiguration.cancelTriggerTask(taskId);
	}


	public void resetTask(String taskId, String taskName,String corn){
		TriggerTask triggerTask = new TriggerTask(new Task(taskName), new CronTrigger(corn));
		taskConfiguration.resetTriggerTask(taskId,triggerTask);
	}

	public void runJobNow(String taskName) {
		Task task = new Task(taskName);
		executorService.execute(task);
	}

	public boolean inited() {
		return taskConfiguration.inited();
	}


	/**
	 * 任务包装类
	 */
	static class Task implements Runnable{
		String task;
		public Task(String task){
			this.task = task;
		}


		//具体业务
		@Override
		public void run() {
			log.info("开始执行定时任务。执行开始时间为：{}", LocalDateTime.now());
			//获取需要执行的定时任务
			IJob job = (IJob) SpringContextUtils.getBean(task);
			job.doJob();
			//System.out.println(task+":"+ LocalDateTime.now()+","+Thread.currentThread().getName());
			log.info("开始执行定时任务。执行结束时间为：{}", LocalDateTime.now());
		}
	}
}

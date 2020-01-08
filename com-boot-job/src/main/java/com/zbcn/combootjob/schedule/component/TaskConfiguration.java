package com.zbcn.combootjob.schedule.component;

import com.zbcn.common.utils.BeanUtils;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  @title TaskConfiguration
 *  @Description 正确的使用方式是全局使用一个  @EnableScheduling， 现在项目中引入了多个
 *  @author zbcn8
 *  @Date 2020/1/7 19:50
 */
@Component
@EnableScheduling
public class TaskConfiguration implements SchedulingConfigurer {

	private final String FIELD_SCHEDULED_TASKS = "scheduledTasks";
	private ScheduledTaskRegistrar taskRegistrar;
	private Set<ScheduledTask> scheduledTasks = null;
	private Map<String, ScheduledTask> tasks = new ConcurrentHashMap<>();

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		this.taskRegistrar = taskRegistrar;
	}

	/**
	 * 获取需要执行的任务集合
	 * @return
	 */
	private Set<ScheduledTask> getScheduledTasks(){
		if (scheduledTasks == null){
			try{
				scheduledTasks = (Set<ScheduledTask>) BeanUtils.getProperty(taskRegistrar, FIELD_SCHEDULED_TASKS);
			}catch (NoSuchFieldException e){
				throw new SchedulingException("not found scheduledFutures field.");
			}
		}
		return scheduledTasks;
	}

	/**
	 * 添加定时任务
	 * @param taskId
	 * @param triggerTask
	 */
	public void addTriggerTask(String taskId, TriggerTask triggerTask){
		if (tasks.containsKey(taskId)){
			throw new SchedulingException("the taskId[" + taskId + "] was added.");
		}
		//第一次初始化 taskRegistrar 可能 为空，schedule 还没加载完毕
		if(Objects.nonNull(taskRegistrar)){
			ScheduledTask scheduledTask = taskRegistrar.scheduleTriggerTask(triggerTask);
			getScheduledTasks().add(scheduledTask);
			tasks.put(taskId, scheduledTask);
		}

	}

	/**
	 * 推出定时任务
	 * @param taskId
	 */
	public void cancelTriggerTask(String taskId){
		ScheduledTask scheduledTask = tasks.get(taskId);
		if (scheduledTask != null){
			scheduledTask.cancel();
		}
		tasks.remove(taskId);
		getScheduledTasks().remove(scheduledTask);
	}

	/**
	 * 重置定时任务
	 * @param taskId
	 * @param triggerTask
	 */
	public void resetTriggerTask(String taskId, TriggerTask triggerTask){
		cancelTriggerTask(taskId);
		addTriggerTask(taskId, triggerTask);
	}

	/**
	 * 任务编号集合
	 * @return
	 */
	public Set<String> taskIds(){
		return tasks.keySet();
	}

	/**
	 * 任务是否存在
	 * @param taskId
	 * @return
	 */
	public boolean hasTask(String taskId){
		return this.tasks.containsKey(taskId);
	}

	/**
	 * 任务调度是否已经初始化完成
	 *
	 * @return
	 */
	public boolean inited(){
		return this.taskRegistrar != null && this.taskRegistrar.getScheduler() != null;
	}







}

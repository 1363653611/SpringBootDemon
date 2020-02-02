package com.zbcn.combootredis.config;

import com.zbcn.combootredis.entity.LikeTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *  @title QuartzConfig
 *  @Description 定时任务配置(点赞人数定时任务)
 *  @author zbcn8
 *  @Date 2020/2/1 20:00
 */
@Configuration
public class QuartzConfig {

	private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";

	@Bean
	public JobDetail quartzDetail(){
		return JobBuilder.newJob(LikeTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
	}

	@Bean
	public Trigger quartzTrigger(){
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  //设置时间周期单位秒
//				.withIntervalInHours(2)  //两个小时执行一次
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(quartzDetail())
				.withIdentity(LIKE_TASK_IDENTITY)
				.withSchedule(scheduleBuilder)
				.build();
	}

}

package com.zbcn.combootjob.component;

import com.zbcn.combootjob.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 *  @title ScheduleJobInitListener
 *  @Description
 *  CommandLineRunner 接口被用作将其加入spring容器中时执行其run方法。
 *  多个CommandLineRunner可以被同时执行在同一个spring上下文中并且执行顺序是以order注解的参数顺序一致。
 *  @author zbcn8
 *  @Date 2020/1/2 11:11
 */
@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {
	@Autowired
	TaskService scheduleJobService;

	@Override
	public void run(String... arg0) throws Exception {
		try {
			scheduleJobService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

package com.zbcn.combootjob.schedule.component;

import com.zbcn.combootjob.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *  @title ScheduleJobInitListener
 *  @Description 定时任务初始化
 *  @author zbcn8
 *  @Date 2020/1/2 11:11
 */
@Component
public class ScheduleJobInitListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ScheduleService ScheduleServiceImpl;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			ScheduleServiceImpl.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

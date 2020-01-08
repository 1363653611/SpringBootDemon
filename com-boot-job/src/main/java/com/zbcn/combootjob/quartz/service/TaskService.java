package com.zbcn.combootjob.quartz.service;

import com.zbcn.combootjob.quartz.entity.TaskDO;
import com.zbcn.combootjob.quartz.entity.query.TaskQuery;
import com.zbcn.common.response.ResponseResult;
import org.quartz.SchedulerException;

public interface TaskService {

	TaskDO get(Long id);

	ResponseResult list(TaskQuery query);

	int save(TaskDO taskScheduleJob);

	int update(TaskDO taskScheduleJob);

	int remove(Long id);

	int removeBatch(Long[] ids);

	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

	void updateCron(Long jobId) throws SchedulerException;

	void run(TaskDO scheduleJob) throws SchedulerException;
}

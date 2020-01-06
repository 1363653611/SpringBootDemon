package com.zbcn.combootjob.service;

import com.zbcn.combootjob.entity.TaskDO;
import com.zbcn.combootjob.entity.query.TaskQuery;
import com.zbcn.combootjob.response.ResponseResult;
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

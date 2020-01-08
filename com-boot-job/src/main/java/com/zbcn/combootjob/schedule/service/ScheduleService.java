package com.zbcn.combootjob.schedule.service;

import com.zbcn.combootjob.schedule.entity.TaskDO;
import com.zbcn.combootjob.schedule.entity.query.TaskQuery;
import com.zbcn.common.response.ResponseResult;

public interface ScheduleService {

	TaskDO get(String id);

	ResponseResult list(TaskQuery query);

	int save(TaskDO taskScheduleJob, String username);

	int update(TaskDO taskScheduleJob, String username);

	int remove(String id);

	int removeBatch(String[] ids);

	void initSchedule();

	void changeStatus(String jobId, String jobStatus, String username);

	void updateCron(String jobId, String username);

	void run(TaskDO scheduleJob);
}

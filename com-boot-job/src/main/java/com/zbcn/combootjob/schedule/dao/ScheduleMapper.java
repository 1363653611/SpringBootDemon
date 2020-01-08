package com.zbcn.combootjob.schedule.dao;

import com.zbcn.combootjob.schedule.entity.TaskDO;
import com.zbcn.combootjob.schedule.entity.query.TaskQuery;

import java.util.List;

/**
 * 任务类mapper
 */
public interface ScheduleMapper {

	TaskDO get(String id);

	List<TaskDO> list();

	List<TaskDO> listTaskVoByDesc(TaskQuery query);

	int save(TaskDO task);

	int update(TaskDO task);

	int remove(String id);

	int removeBatch(String[] ids);
}

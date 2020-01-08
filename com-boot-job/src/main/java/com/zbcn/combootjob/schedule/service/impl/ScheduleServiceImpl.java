package com.zbcn.combootjob.schedule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbcn.combootjob.schedule.component.TaskManager;
import com.zbcn.combootjob.schedule.dao.ScheduleMapper;
import com.zbcn.combootjob.schedule.entity.TaskDO;
import com.zbcn.combootjob.schedule.entity.query.TaskQuery;
import com.zbcn.combootjob.schedule.enums.JobStatusEnum;
import com.zbcn.combootjob.schedule.service.ScheduleService;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.common.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *  @title TaskServiceImpl
 *  @Description 定时任务service
 *  @author zbcn8
 *  @Date 2020/1/2 17:07
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Resource
	private ScheduleMapper scheduleMapper;

	@Autowired
	private TaskManager taskManager;


	@Override
	public TaskDO get(String id) {
		return scheduleMapper.get(id);
	}

	@Override
	public ResponseResult list(TaskQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit());
		List<TaskDO> list = scheduleMapper.listTaskVoByDesc(query);
		//取记录总条数
		PageInfo<TaskDO> pageInfo = new PageInfo<>(list);
		//创建一个返回值对象
		return ResponseResult.success(pageInfo);
	}

	@Override
	public int save(TaskDO task, String username) {
		String id = MD5Utils.encrypt(task.getJobName() + task.getJobGroup());
		task.setId(id);
		task.setJobStatus(JobStatusEnum.STOP.getCode());
		task.setCreateUser(username);
		task.setCreateTime(new Date());
		return scheduleMapper.save(task);
	}

	@Override
	public int update(TaskDO task,String userName) {
		task.setUpdateUser(userName);
		task.setUpdateTime(new Date());
		return scheduleMapper.update(task);
	}

	@Override
	public int remove(String id) {
		taskManager.removeTask(id);
		return scheduleMapper.remove(id);
	}

	@Override
	public int removeBatch(String[] ids) {
		for (String id : ids) {
			remove(id);
		}
		return scheduleMapper.removeBatch(ids);
	}

	@Override
	public void initSchedule(){
		// 这里获取任务信息数据
		List<TaskDO> jobList = scheduleMapper.list();
		for (TaskDO task : jobList) {
			if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
				String id = task.getId();
				taskManager.addTask(id,task.getJobName(),task.getCronExpression());
			}
		}
	}

	@Override
	public void changeStatus(String jobId, String jobStatus,String username) {
		TaskDO task = get(jobId);
		if (task == null) {
			return;
		}
		if (JobStatusEnum.STOP.getCode().equals(jobStatus)) {
			taskManager.removeTask(jobId);
			task.setJobStatus(JobStatusEnum.STOP.getCode());
		} else {
			task.setJobStatus(JobStatusEnum.RUNNING.getCode());
			taskManager.addTask(jobId,task.getJobName(),task.getCronExpression());
		}
		update(task,username);
	}

	@Override
	public void updateCron(String jobId,String username) {
		TaskDO task = get(jobId);
		if (task == null) {
			return;
		}
		if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
			taskManager.resetTask(jobId,task.getJobName(),task.getCronExpression());
		}
		update(task,username);
	}

	@Override
	public void run(TaskDO task) {
		taskManager.runJobNow(task.getJobName());
	}


}

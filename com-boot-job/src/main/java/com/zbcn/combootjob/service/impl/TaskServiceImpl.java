package com.zbcn.combootjob.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbcn.combootjob.component.QuartzManager;
import com.zbcn.combootjob.entity.TaskDO;
import com.zbcn.combootjob.entity.query.TaskQuery;
import com.zbcn.combootjob.entity.vo.TaskVO;
import com.zbcn.combootjob.enums.JobStatusEnum;
import com.zbcn.combootjob.mapper.TaskMapper;
import com.zbcn.combootjob.response.ResponseResult;
import com.zbcn.combootjob.service.TaskService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	@Resource
	private TaskMapper taskMapper;

	@Autowired
	QuartzManager quartzManager;


	@Override
	public TaskDO get(Long id) {
		return taskMapper.get(id);
	}

	@Override
	public ResponseResult list(TaskQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit());
		List<TaskVO> list = taskMapper.listTaskVoByDesc(query.getDescription());
		//取记录总条数
		PageInfo<TaskVO> pageInfo = new PageInfo<TaskVO>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		return ResponseResult.success(pageInfo);
	}

	@Override
	public int save(TaskDO task) {
		task.setJobStatus(JobStatusEnum.STOP.getCode());
		task.setCreateUser("test");
		task.setCreateTime(new Date());
		task.setUpdateUser("test");
		task.setUpdateTime(new Date());
		return taskMapper.save(task);
	}

	@Override
	public int update(TaskDO task) {
		return taskMapper.update(task);
	}

	@Override
	public int remove(Long id) {
		try {
			TaskDO task = get(id);
			quartzManager.deleteJob(task);
			return taskMapper.remove(id);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int removeBatch(Long[] ids) {
		for (Long id : ids) {
			try {
				TaskDO task = get(id);
				quartzManager.deleteJob(task);
			} catch (SchedulerException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return taskMapper.removeBatch(ids);
	}

	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		List<TaskDO> jobList = taskMapper.list();
		for (TaskDO task : jobList) {
			if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
				quartzManager.addJob(task);
			}
		}
	}

	@Override
	public void changeStatus(Long jobId, String jobStatus) throws SchedulerException {
		TaskDO task = get(jobId);
		if (task == null) {
			return;
		}
		if (JobStatusEnum.STOP.getCode().equals(jobStatus)) {
			quartzManager.deleteJob(task);
			task.setJobStatus(JobStatusEnum.STOP.getCode());
		} else {
			task.setJobStatus(JobStatusEnum.RUNNING.getCode());
			quartzManager.addJob(task);
		}
		update(task);
	}

	@Override
	public void updateCron(Long jobId) throws SchedulerException {
		TaskDO task = get(jobId);
		if (task == null) {
			return;
		}
		if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
			quartzManager.updateJobCron(task);
		}
		update(task);
	}

	@Override
	public void run(TaskDO task) throws SchedulerException {
		quartzManager.runJobNow(task);
	}


}

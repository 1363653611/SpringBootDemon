package com.zbcn.combootjob.quartz.controller;

import com.zbcn.combootjob.quartz.entity.TaskDO;
import com.zbcn.combootjob.quartz.entity.query.TaskQuery;
import com.zbcn.combootjob.quartz.enums.JobStatusEnum;
import com.zbcn.combootjob.quartz.service.TaskService;
import com.zbcn.common.response.ResponseResult;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  @title TaskController
 *  @Description 定时任务控制器
 *  @author zbcn8
 *  @Date 2020/1/2 13:38
 */
@RestController
@RequestMapping("/management/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/list")
	public ResponseResult list(TaskQuery query) {
		// 查询列表数据
		ResponseResult result = taskService.list(query);
		return result;
	}

	@PostMapping("/edit")
	public ResponseResult<Object> edit(TaskDO task) {
		TaskDO taskServer = taskService.get(task.getId());
		if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
			return ResponseResult.fail("修改之前请先停止任务！");
		}
		taskService.update(task);
		return ResponseResult.success("修改成功");
	}

	@GetMapping("/changeStatus/{id}")
	public ResponseResult changeStatus(@PathVariable("id") Long id, Boolean jobStatus) {
		String status = jobStatus == true ? JobStatusEnum.RUNNING.getCode() : JobStatusEnum.STOP.getCode();
		try {
			taskService.changeStatus(id, status);
			return ResponseResult.success("状态修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseResult.fail("任务状态修改失败");
	}

	/**
	 * 删除
	 */
	@GetMapping("/remove/{id}")
	public ResponseResult remove(@PathVariable("id") Long id) {
		TaskDO taskServer = taskService.get(id);
		if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
			return ResponseResult.fail( "删除前请先停止任务！");
		}
		if (taskService.remove(id) > 0) {
			return ResponseResult.success("删除定时任务成功");
		}
		return ResponseResult.fail( "删除任务失败！");
	}

	/**
	 * 批量删除定时任务
	 * @param ids
	 * @return
	 */
	@GetMapping("/removeBatch")
	public ResponseResult removeBatch(@RequestParam("ids[]") Long[] ids) {
		for (Long id : ids) {
			TaskDO taskServer = taskService.get(id);
			if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
				return ResponseResult.fail( "删除前请先停止任务！");
			}
		}
		taskService.removeBatch(ids);
		return ResponseResult.success("删除成功");
	}

	/**
	 * 立即运行
	 */
	@GetMapping("/run/{id}")
	public ResponseResult run(@PathVariable("id") Long id) {
		TaskDO taskServer = taskService.get(id);
		try {
			if (JobStatusEnum.STOP.getCode().equals(taskServer.getJobStatus())) {
				return ResponseResult.fail( "立即执行请先开启任务！");
			}
			taskService.run(taskServer);
			return ResponseResult.success("定时任务启动成功");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return ResponseResult.fail("立即运行任务失败！");
	}

	/**
	 * 新增保存
	 */
	@PostMapping("/save")
	public ResponseResult save(TaskDO task) {
		if (taskService.save(task) > 0) {
			return ResponseResult.success("新增定时任务成功");
		}
		return ResponseResult.fail("新增任务失败！");
	}

	/**
	 * 更新定时任务:将数据库中的定时任务更新到 scheduler 中
	 * @param id
	 * @return
	 */
	@PatchMapping("/update/{id}")
	public ResponseResult updateCore(@PathVariable("id") Long id){
		try {
			taskService.updateCron(id);
			return ResponseResult.success("更新定时任务成功");
		} catch (SchedulerException e) {
			e.printStackTrace();
			return ResponseResult.success("更新定时任务失败");
		}

	}


}

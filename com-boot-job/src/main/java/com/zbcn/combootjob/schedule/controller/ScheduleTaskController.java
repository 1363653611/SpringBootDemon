package com.zbcn.combootjob.schedule.controller;

import com.zbcn.combootjob.schedule.entity.TaskDO;
import com.zbcn.combootjob.schedule.entity.query.TaskQuery;
import com.zbcn.combootjob.schedule.enums.JobStatusEnum;
import com.zbcn.combootjob.schedule.service.ScheduleService;
import com.zbcn.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  @title JobTaskController
 *  @Description 定时任务配置类
 *  @author zbcn8
 *  @Date 2020/1/2 16:55
 */
@RestController
@RequestMapping("/schedule/task")
public class ScheduleTaskController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/list")
	public ResponseResult list(TaskQuery query) {
		// 查询列表数据
		ResponseResult result = scheduleService.list(query);
		return result;
	}

	@PostMapping("/edit")
	public ResponseResult<Object> edit(TaskDO task, HttpServletRequest request) {
		String userName = getUserName(request);
		TaskDO taskServer = scheduleService.get(task.getId());
		if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
			return ResponseResult.fail("修改之前请先停止任务！");
		}
		scheduleService.update(task,userName);
		return ResponseResult.success("修改成功");
	}

	@GetMapping("/changeStatus/{id}")
	public ResponseResult changeStatus(@PathVariable("id") String id, Boolean jobStatus, HttpServletRequest request) {
		String userName = getUserName(request);
		String status = jobStatus == true ? JobStatusEnum.RUNNING.getCode() : JobStatusEnum.STOP.getCode();
		try {
			scheduleService.changeStatus(id, status,userName);
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
	public ResponseResult remove(@PathVariable("id") String id) {
		TaskDO taskServer = scheduleService.get(id);
		if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
			return ResponseResult.fail( "删除前请先停止任务！");
		}
		if (scheduleService.remove(id) > 0) {
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
	public ResponseResult removeBatch(@RequestParam("ids[]") String[] ids) {
		for (String id : ids) {
			TaskDO taskServer = scheduleService.get(id);
			if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
				return ResponseResult.fail( "删除前请先停止任务！");
			}
		}
		scheduleService.removeBatch(ids);
		return ResponseResult.success("删除成功");
	}

	/**
	 * 立即运行
	 */
	@GetMapping("/run/{id}")
	public ResponseResult run(@PathVariable("id") String id) {
		TaskDO task = scheduleService.get(id);
		try {
			if (JobStatusEnum.STOP.getCode().equals(task.getJobStatus())) {
				return ResponseResult.fail( "立即执行请先开启任务！");
			}
			scheduleService.run(task);
			return ResponseResult.success("定时任务执行成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseResult.fail("立即运行任务失败！");
	}

	/**
	 * 新增保存
	 */
	@PostMapping("/save")
	public ResponseResult save(TaskDO task, HttpServletRequest request) {
		String userName = getUserName(request);
		if (scheduleService.save(task,userName) > 0) {
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
	public ResponseResult updateCore(@PathVariable("id") String id, HttpServletRequest request){
		String userName = getUserName(request);
		try {
			scheduleService.updateCron(id,userName);
			return ResponseResult.success("更新定时任务成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseResult.success("更新定时任务失败");
		}

	}

	private String getUserName(HttpServletRequest request) {
		return "zbcn";
	}
}

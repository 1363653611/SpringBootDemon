package com.zbcn.combootjob.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskVO implements Serializable {

	private static final long serialVersionUID = -5405638533110159707L;
	private Long id;
	// 任务名
	private String jobName;
	// 任务描述
	private String description;
	// 任务分组
	private String jobGroup;
	// 任务执行时调用哪个类的方法 包名+类名
	private String beanClass;
	// cron表达式
	private String cronExpression;
	// 任务状态
	private String jobStatus;
}

package com.zbcn.combootjob.schedule.entity.query;

import java.io.Serializable;

public class TaskQuery implements Serializable {


	private static final long serialVersionUID = -4848808840513694676L;
	// 任务描述
	private String description;

	// 任务名
	private String jobName;

	// 任务分组
	private String jobGroup;

	private int page;

	private int limit;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}

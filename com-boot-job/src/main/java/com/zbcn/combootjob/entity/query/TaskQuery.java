package com.zbcn.combootjob.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskQuery extends BaseQuery implements Serializable {


	private static final long serialVersionUID = -4848808840513694676L;
	// 任务描述
	private String description;

}

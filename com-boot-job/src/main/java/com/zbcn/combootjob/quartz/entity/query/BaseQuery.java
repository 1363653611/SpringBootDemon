package com.zbcn.combootjob.quartz.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseQuery implements Serializable {

	private static final long serialVersionUID = 7697241442492911728L;
	private int page;

	private int limit;
}

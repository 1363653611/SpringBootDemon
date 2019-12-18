package com.zbcn.combootsecurity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 2166318916452202302L;

	private Integer userId;

	private Integer roleId;
}

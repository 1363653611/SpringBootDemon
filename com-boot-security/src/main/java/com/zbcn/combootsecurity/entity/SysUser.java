package com.zbcn.combootsecurity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
	private static final long serialVersionUID = -7904683638537401381L;

	private Integer id;

	private String name;

	private String password;
}

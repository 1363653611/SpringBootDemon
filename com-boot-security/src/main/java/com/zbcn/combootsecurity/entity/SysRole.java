package com.zbcn.combootsecurity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
	private static final long serialVersionUID = 6222166350715031511L;

	private Integer id;

	private String name;
}

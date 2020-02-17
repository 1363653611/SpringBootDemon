package com.zbcn.shiroauthentication.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 7901136282611881342L;
	private Integer id;
	private String name;
	private String memo;
}

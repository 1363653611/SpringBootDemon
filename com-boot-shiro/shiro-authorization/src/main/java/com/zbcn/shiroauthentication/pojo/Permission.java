package com.zbcn.shiroauthentication.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
	private static final long serialVersionUID = 6496453247461510796L;
	private Integer id;
	private String url;
	private String name;
}

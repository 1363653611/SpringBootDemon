package com.zbcn.shiroauthentication.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = 8469330374796228779L;
	private Integer id;
	private String userName;
	private String password;
	private Date createTime;
	private String status;
}

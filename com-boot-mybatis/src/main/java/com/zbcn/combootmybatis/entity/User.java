package com.zbcn.combootmybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = -6351795888777520643L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}

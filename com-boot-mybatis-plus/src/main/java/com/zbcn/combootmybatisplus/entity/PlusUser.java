package com.zbcn.combootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PlusUser {

	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private Integer age;
	private String email;
}

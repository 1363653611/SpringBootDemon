package com.zbcn.combootredis.enums;

import lombok.Getter;

/**
 *  @title LikedStatusEnum
 *  @Description 用户点赞的状态
 *  @author zbcn8
 *  @Date 2020/1/17 16:09
 */
@Getter
public enum LikedStatusEnum {
	LIKE(1, "点赞"),
	UNLIKE(0, "取消点赞/未点赞"),
	;
	private Integer code;

	private String msg;

	LikedStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}

package com.zbcn.combootredis.entity;

import com.zbcn.combootredis.enums.LikedStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLike {

	//主键id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//被点赞的用户的id
	private String likedUserId;

	//点赞的用户的id
	private String likedPostId;

	//点赞的状态.默认未点赞
	private Integer status = LikedStatusEnum.UNLIKE.getCode();

}

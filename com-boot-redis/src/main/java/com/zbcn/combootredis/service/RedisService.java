package com.zbcn.combootredis.service;

import com.zbcn.combootredis.entity.UserLike;
import com.zbcn.combootredis.entity.dto.LikedCountDTO;

import java.util.List;

/**
 *  @title RedisService
 *  @Description 点赞操作
 *  @author zbcn8
 *  @Date 2020/1/17 16:04
 */
public interface RedisService{
	/**
	 * 点赞。状态为1
	 * @param likedUserId
	 * @param likedPostId
	 */
	void saveLiked2Redis(String likedUserId, String likedPostId);

	/**
	 * 取消点赞。将状态改变为0
	 * @param likedUserId
	 * @param likedPostId
	 */
	void unlikeFromRedis(String likedUserId, String likedPostId);

	/**
	 * 从Redis中删除一条点赞数据
	 * @param likedUserId
	 * @param likedPostId
	 */
	void deleteLikedFromRedis(String likedUserId, String likedPostId);

	/**
	 * 该用户的点赞数加1
	 * @param likedUserId
	 */
	void incrementLikedCount(String likedUserId);

	/**
	 * 该用户的点赞数减1
	 * @param likedUserId
	 */
	void decrementLikedCount(String likedUserId);

	/**
	 * 获取Redis中存储的所有点赞数据
	 * @return
	 */
	List<UserLike> getLikedDataFromRedis();

	/**
	 * 获取Redis中存储的所有点赞数量
	 * @return
	 */
	List<LikedCountDTO> getLikedCountFromRedis();
}

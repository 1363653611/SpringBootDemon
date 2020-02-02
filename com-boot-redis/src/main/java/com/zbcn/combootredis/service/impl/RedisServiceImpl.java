package com.zbcn.combootredis.service.impl;

import com.zbcn.combootredis.entity.UserLike;
import com.zbcn.combootredis.entity.dto.LikedCountDTO;
import com.zbcn.combootredis.enums.LikedStatusEnum;
import com.zbcn.combootredis.service.RedisService;
import com.zbcn.combootredis.utils.RedisKeyUtils;
import com.zbcn.combootredis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void saveLiked2Redis(String likedUserId, String likedPostId) {
		String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
		redisUtil.hset(RedisKeyUtils.MAP_KEY_USER_LIKED,key, LikedStatusEnum.LIKE.getCode());
	}

	@Override
	public void unlikeFromRedis(String likedUserId, String likedPostId) {
		String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
		redisUtil.hset(RedisKeyUtils.MAP_KEY_USER_LIKED,key, LikedStatusEnum.UNLIKE.getCode());
	}

	@Override
	public void deleteLikedFromRedis(String likedUserId, String likedPostId) {
		String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
		redisUtil.hdel(RedisKeyUtils.MAP_KEY_USER_LIKED,key);
	}

	@Override
	public void incrementLikedCount(String likedUserId) {
		redisUtil.hincr(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedUserId, 1);
	}

	@Override
	public void decrementLikedCount(String likedUserId) {
		redisUtil.hdecr(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,likedUserId,1);
	}

	@Override
	public List<UserLike> getLikedDataFromRedis() {
		Cursor cursor = redisUtil.hScan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
		ArrayList<UserLike> list = new ArrayList<>();
		while (cursor.hasNext()){
			Map.Entry<Object, Object> entry  = (Map.Entry<Object, Object>) cursor.next();
			String key = (String) entry.getKey();
			//分离出 likedUserId，likedPostId
			String[] split = key.split("::");
			String likedUserId = split[0];
			String likedPostId = split[1];
			Integer value = (Integer) entry.getValue();
			//组装成 UserLike 对象
			UserLike userLike = UserLike.builder().likedPostId(likedPostId).status(value).likedUserId(likedUserId).build();
			list.add(userLike);
			//存到 list 后从 Redis 中删除
			redisUtil.hdel(RedisKeyUtils.MAP_KEY_USER_LIKED,key);
		}
		return list;
	}

	@Override
	public List<LikedCountDTO> getLikedCountFromRedis() {
		Cursor cursor = redisUtil.hScan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
		List<LikedCountDTO> list = new ArrayList<>();
		while(cursor.hasNext()){
			Map.Entry<Object, Object> map = (Map.Entry<Object, Object>) cursor.next();
			//将点赞数量存储在 LikedCountDT
			String key = (String)map.getKey();
			LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
			list.add(dto);
			//从Redis中删除这条记录
			redisUtil.hdel(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT,key);
		}
		return list;
	}
}

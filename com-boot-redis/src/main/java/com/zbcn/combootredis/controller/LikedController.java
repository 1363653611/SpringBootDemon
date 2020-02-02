package com.zbcn.combootredis.controller;

import com.zbcn.combootredis.entity.UserLike;
import com.zbcn.combootredis.service.LikedService;
import com.zbcn.combootredis.service.RedisService;
import com.zbcn.common.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 *  @title LikedController
 *  @Description
 *  用 Redis 存储两种数据，一种是记录点赞人、被点赞人、点赞状态的数据，另一种是每个用户被点赞了多少次，做个简单的计数。
 *  由于需要记录点赞人和被点赞人，还有点赞状态（点赞、取消点赞），还要固定时间间隔取出 Redis 中所有点赞数据，分析了下 Redis 数据格式中 Hash 最合适。
 *  @author zbcn8
 *  @Date 2020/1/17 16:00
 */
@Api("用户点赞功能")
@RequestMapping("/redis/liked")
@RestController
public class LikedController {

	@Autowired
	private LikedService likedServiceImpl;

	@Autowired
	private RedisService redisService;

	@ApiOperation("点赞")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "likedUserId", value = "被点赞的用户id"),
			@ApiImplicitParam(name = "likedPostId", value = "点赞的用户id")
	})
	@GetMapping("/like")
	public ResponseResult like(@RequestParam("likedUserId") String likedUserId,
	                           @RequestParam("likedPostId") String likedPostId){
		//先把数据存到Redis里,再定时存回数据库
		redisService.saveLiked2Redis(likedUserId, likedPostId);
		redisService.incrementLikedCount(likedUserId);
		return ResponseResult.success("保存点赞人数成功.");
	}

	@GetMapping("/unlike")
	@ApiOperation("取消点赞")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "likedUserId", value = "被点赞的用户id"),
			@ApiImplicitParam(name = "likedPostId", value = "点赞的用户id")
	})
	public ResponseResult unlike(@RequestParam("likedUserId") String likedUserId,
	                       @RequestParam("likedPostId") String likedPostId) {
		//取消点赞,先存到Redis里面，再定时写到数据库里
		redisService.unlikeFromRedis(likedUserId, likedPostId);
		redisService.decrementLikedCount(likedUserId);
		return ResponseResult.success("取消点赞功能成功");
	}

	@GetMapping("/likedListByLikedId")
	public ResponseResult getLikedListByLikedUserId(String likedUserId,int pageNo, int pageSize){
		Pageable pageable = PageRequest.of(pageNo,pageSize);
		Page<UserLike> likedListByLikedUserId = likedServiceImpl.getLikedListByLikedUserId(likedUserId, pageable);
		return ResponseResult.success(likedListByLikedUserId);
	}

	@GetMapping("/likedListByPostId")
	public ResponseResult getLikedListByLikedPostId(String PostUserId,int pageNo, int pageSize){
		Pageable pageable = PageRequest.of(pageNo,pageSize);
		Page<UserLike> likedListByLikedPostId = likedServiceImpl.getLikedListByLikedPostId(PostUserId, pageable);
		return ResponseResult.success(likedListByLikedPostId);
	}




}

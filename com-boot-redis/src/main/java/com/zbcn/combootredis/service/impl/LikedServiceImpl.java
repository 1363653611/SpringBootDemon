package com.zbcn.combootredis.service.impl;

import com.zbcn.combootredis.entity.UserLike;
import com.zbcn.combootredis.entity.dto.LikedCountDTO;
import com.zbcn.combootredis.enums.LikedStatusEnum;
import com.zbcn.combootredis.repository.UserLikeRepository;
import com.zbcn.combootredis.service.LikedService;
import com.zbcn.combootredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LikedServiceImpl implements LikedService {

	@Autowired
	UserLikeRepository likeRepository;

	@Autowired
	RedisService redisService;


	@Transactional
	@Override
	public UserLike save(UserLike userLike) {
		return likeRepository.save(userLike);
	}

	@Transactional
	@Override
	public List<UserLike> saveAll(List<UserLike> list) {
		return likeRepository.saveAll(list);
	}

	@Override
	public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
		return likeRepository.findByLikedUserIdAndStatus(likedUserId, LikedStatusEnum.LIKE.getCode(), pageable);
	}

	@Override
	public Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable) {
		return likeRepository.findByLikedPostIdAndStatus(likedPostId, LikedStatusEnum.LIKE.getCode(), pageable);
	}

	@Override
	public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
		return likeRepository.findByLikedUserIdAndLikedPostId(likedUserId, likedPostId);
	}

	@Transactional
	@Override
	public void transLikedFromRedis2DB() {
		List<UserLike> list = redisService.getLikedDataFromRedis();
		for (UserLike like : list) {
			UserLike ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedPostId());
			if (ul == null){
				//没有记录，直接存入
				save(like);
			}else{
				//有记录，需要更新
				ul.setStatus(like.getStatus());
				save(ul);
			}
		}
	}

	@Override
	public void transLikedCountFromRedis2DB() {
		List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
		//TODO

	}
}

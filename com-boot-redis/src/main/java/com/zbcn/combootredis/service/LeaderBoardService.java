package com.zbcn.combootredis.service;

import com.zbcn.combootredis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 排行榜操作 service
 */
@Service
public class LeaderBoardService {

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 添加
	 * @param item
	 * @return
	 */
	public double incrLeader(String key, String item, Double score){
		// 今日
		//String key = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return redisUtil.zSetIncrBy(key, item, score);
	}

	/**
	 *  @title LeaderboardService
	 *  @Description 获取降序排行榜
	 *  @author zbcn8
	 *  @Date 2020/1/17 13:13
	 */
	public List<Object> getLeaderBoards(String key, int start, int end){
		Object[] objects = redisUtil.zSetSortedListWithScore(key, start, end);
		 return Arrays.asList(objects);
	}

	/**
	 * 删除key所对应的数据
	 * @param key
	 * @return
	 */
	public Long removeAll(String key){
		return redisUtil.zSetRemoveByKey(key);
	}

	/**
	 * 单独添加指标信息
	 * @param key
	 * @param item
	 * @param score
	 * @return
	 */
	public boolean addLeader(String key, String item,double score){
		return redisUtil.zSetAdd(key,item,score);
	}


	/**
	 * 批量添加指标
	 * @param key
	 * @param leaders
	 * @return
	 */
	public Long batchAddLeaders(String key,List<Map> leaders){
		Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
		leaders.forEach( map ->{
			DefaultTypedTuple<Object> tuple = new DefaultTypedTuple((String)map.get("item"), (Double) map.get("score"));
			tuples.add(tuple);
		});

		 return redisUtil.ZSetBatchAdd(key, tuples);
	}


}

package com.zbcn.combootredis.controller;

import com.zbcn.combootredis.entity.dto.Leader;
import com.zbcn.combootredis.service.LeaderBoardService;
import com.zbcn.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @title RedisController
 *  @Description redis 实现排行榜功能
 *  @author zbcn8
 *  @Date 2020/1/16 18:35
 */
@RestController
@RequestMapping("/redis/leaderBoard")
public class LeaderBoardController {

	@Autowired
	private LeaderBoardService leaderboardService;

	/**
	 * 添加排行榜
	 * @param leader
	 * @return
	 */
	@PostMapping("/add")
	public ResponseResult addLeader(Leader leader){

		boolean b = leaderboardService.addLeader(leader.getKey(), leader.getItem(), leader.getScore());
		return ResponseResult.success("添加成功。");
	}

	/**
	 * 获取指定排行榜
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("/getList/{key}")
	public ResponseResult getLeaderBoards(@PathVariable String key, @RequestParam Integer start, @RequestParam Integer end ){
		List<Object> leaderBoards = leaderboardService.getLeaderBoards(key, start, end);
		return ResponseResult.success(leaderBoards);
	}

	/**
	 * 已有排行榜增加分值
	 * @param leader
	 * @return
	 */
	@PutMapping("/incr")
	public ResponseResult incrLeaderScore(Leader leader){
		double v = leaderboardService.incrLeader(leader.getKey(), leader.getItem(), leader.getScore());
		return ResponseResult.success(v);
	}
}

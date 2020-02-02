package com.zbcn.combootredis.entity;

import com.zbcn.combootredis.service.LikedService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @title LikeTask
 *  @Description 点赞定时任务
 *  @author zbcn8
 *  @Date 2020/2/1 20:05
 */
@Slf4j
public class LikeTask extends QuartzJobBean {
	@Autowired
	LikedService likedService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		log.info("LikeTask-------- {}", sdf.format(new Date()));

		//将 Redis 里的点赞信息同步到数据库里
		likedService.transLikedFromRedis2DB();
		//likedService.transLikedCountFromRedis2DB();

	}
}

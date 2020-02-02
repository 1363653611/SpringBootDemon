package com.zbcn.combootredis.entity.dto;

import lombok.Builder;
import lombok.Data;
/**
 *  @title Leader
 *  @Description 排行榜实体类
 *  @author zbcn8
 *  @Date 2020/1/17 13:33
 */
@Data
@Builder
public class Leader {

	private String key;

	private String item;

	private double score;
}

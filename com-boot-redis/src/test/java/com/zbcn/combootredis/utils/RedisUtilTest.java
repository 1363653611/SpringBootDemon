package com.zbcn.combootredis.utils;

import com.alibaba.fastjson.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void testString(){
		boolean set = redisUtil.set("11", 2);
		Object o = redisUtil.get("11");
		Assert.assertEquals(2,o);
		//redisUtil.del("11");
//		o = redisUtil.get("11");
//		assert  o == null;
		long incr = redisUtil.incr("11", 2);
		o = redisUtil.get("11");
		Assert.assertEquals(4,o);
		long incr1 = redisUtil.incr("11", 1);
		System.out.println(incr1);

	}

	@Test
	public void zSetTest(){
		Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
		long start = System.currentTimeMillis();
//		for (int i = 0; i < 100; i++) {
//			DefaultTypedTuple<Object> tuple = new DefaultTypedTuple<>("张三" + i, 1D + i);
//			tuples.add(tuple);
//		}
		System.out.println("循环时间:" +( System.currentTimeMillis() - start));
		String key = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//		redisUtil.ZSetBatchAdd(key,tuples);

		Object[] objects = redisUtil.zSetSortedList(key, 0, 10);
		System.out.println("排序后："+ JSONArray.toJSON(objects));
		Object[] objects1 = redisUtil.zSetSortedListWithScore(key, 0, 10);
		System.out.println("带分数排序："+JSONArray.toJSON(objects1));

		//
		redisUtil.zSetIncrBy(key,"张三97",100d);
		Object[] objects2 = redisUtil.zSetSortedListWithScore(key, 0, 10);
		System.out.println("修改后带分数排序："+JSONArray.toJSON(objects2));
		//批量删除
		redisUtil.zSetRemoveByKey(key);
	}

}

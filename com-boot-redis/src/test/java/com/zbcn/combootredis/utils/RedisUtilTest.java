package com.zbcn.combootredis.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}

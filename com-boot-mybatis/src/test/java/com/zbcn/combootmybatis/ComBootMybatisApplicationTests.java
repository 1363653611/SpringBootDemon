package com.zbcn.combootmybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbcn.combootmybatis.entity.User;
import com.zbcn.combootmybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComBootMybatisApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ComBootMybatisApplicationTests.class);

	@Resource
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		final int row1 = userMapper.insert(new User("u1", "p1"));
		log.info("[添加结果] - [{}]", row1);
		final int row2 = userMapper.insert(new User("u2", "p2"));
		log.info("[添加结果] - [{}]", row2);
		final int row3 = userMapper.insert(new User("u1", "p3"));
		log.info("[添加结果] - [{}]", row3);
		final List<User> u1 = userMapper.findByUsername("u1");
		log.info("[根据用户名查询] - [{}]", u1);

		//模拟分页
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));
		userMapper.insertSelective(new User("u1", "p1"));

		//// TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
		PageInfo<Object> pageInfo  = PageHelper.startPage(1, 10).setOrderBy("id DESC").doSelectPageInfo(() -> {
			this.userMapper.selectAll();
		});
		log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());
		PageHelper.startPage(1, 10).setOrderBy("id desc");
		final PageInfo<User> userPageInfo = new PageInfo<>(this.userMapper.selectAll());
		log.info("[普通写法] - [{}]", userPageInfo);
	}

}

package com.zbcn.combootmybatis.mapper;

import com.zbcn.combootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * t_user 操作：演示两种方式
 * <p>第一种是基于mybatis3.x版本后提供的注解方式<p/>
 * <p>第二种是早期写法，将SQL写在 XML 中<p/>
 * <p>第三种是通用Mapper与分页插件的集成<p/>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

	/**
	 *  根据用户名查询用户结果集
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM t_user WHERE username = #{username}")
	List<User> findByUsername(@Param("username") String username);

	/**
	 * 保存用户信息
	 *
	 * @param user 用户信息
	 * @return 成功 1 失败 0
	 */
	int insert(User user);

	/**
	 * 根据用户名统计
	 *
	 * @param username 用户名
	 * @return 统计结果
	 */
	int countByUsername(String username);
}

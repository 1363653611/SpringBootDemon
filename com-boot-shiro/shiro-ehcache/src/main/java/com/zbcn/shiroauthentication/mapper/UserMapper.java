package com.zbcn.shiroauthentication.mapper;

import com.zbcn.shiroauthentication.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	User findByUserName(String userName);
}

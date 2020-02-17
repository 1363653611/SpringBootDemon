package com.zbcn.shiroauthentication.mapper;

import com.zbcn.shiroauthentication.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
	List<Role> findByUserName(String userName);
}

package com.zbcn.shiroauthentication.mapper;

import com.zbcn.shiroauthentication.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPermissionMapper {

	List<Permission> findByUserName(String userName);
}

package com.zbcn.combootsecurity.mapper;

import com.zbcn.combootsecurity.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysPermissionMapper {

	@Select("SELECT * FROM sys_permission WHERE role_id=#{roleId}")
	List<SysPermission> listByRoleId(Integer roleId);
}

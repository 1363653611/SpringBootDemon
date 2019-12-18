package com.zbcn.combootsecurity.service;

import com.zbcn.combootsecurity.entity.SysPermission;
import com.zbcn.combootsecurity.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPermissionService {
	@Resource
	private SysPermissionMapper permissionMapper;

	/**
	 * 获取指定角色所有权限
	 */
	public List<SysPermission> listByRoleId(Integer roleId) {
		return permissionMapper.listByRoleId(roleId);
	}

}

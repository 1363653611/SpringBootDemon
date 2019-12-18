package com.zbcn.combootsecurity.service;

import com.zbcn.combootsecurity.entity.SysUserRole;
import com.zbcn.combootsecurity.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserRoleService {

	@Resource
	private SysUserRoleMapper userRoleMapper;

	public List<SysUserRole> listByUserId(Integer userId) {
		return userRoleMapper.listByUserId(userId);
	}

}

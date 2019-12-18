package com.zbcn.combootsecurity.service;

import com.zbcn.combootsecurity.entity.SysRole;
import com.zbcn.combootsecurity.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleService {

	@Resource
	private SysRoleMapper roleMapper;

	public SysRole selectById(Integer id){
		return roleMapper.selectById(id);
	}

	public SysRole selectByName(String name) {
		return roleMapper.selectByName(name);
	}
}

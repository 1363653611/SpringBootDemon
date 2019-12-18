package com.zbcn.combootsecurity.service;

import com.zbcn.combootsecurity.entity.SysUser;
import com.zbcn.combootsecurity.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {
	@Resource
	private SysUserMapper userMapper;

	public SysUser selectById(Integer id) {
		return userMapper.selectById(id);
	}

	public SysUser selectByName(String name) {
		return userMapper.selectByName(name);
	}
}

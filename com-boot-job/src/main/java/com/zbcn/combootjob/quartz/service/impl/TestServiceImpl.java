package com.zbcn.combootjob.quartz.service.impl;

import com.zbcn.combootjob.quartz.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	@Override
	public String doSomeThing() {
		System.out.println("测试获取spring中的bean 执行任务...");
		return "测试获取spring中的bean 执行任务...";
	}
}

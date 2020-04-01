package com.zbcn.combootmdc.controller;

import com.zbcn.combootmdc.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @title DemoController
 *  @Description  测试日志跟踪功能的controller
 *  @author zbcn8
 *  @Date 2020/4/1 15:05
 */
@RestController
public class DemoController {

	private Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService demoService;

	@GetMapping("demon/name")
	public String demo(String name) {
		log.info("name:" + name);
		return demoService.getName();
	}
}

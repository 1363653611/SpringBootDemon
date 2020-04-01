package com.zbcn.combootmdc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	Logger log = LoggerFactory.getLogger(DemoService.class);
	public String getName(){
		log.info("service- []","zhangsan");
		return "zhangsan";
	}
}

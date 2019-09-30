package com.zbcn.duplicaterequest.controller;

import com.zbcn.duplicaterequest.annotation.CacheLock;
import com.zbcn.duplicaterequest.annotation.CacheParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试重复提交的类
 */
@RestController
@RequestMapping("/duplicate")
public class DemonController {

	@CacheLock(prefix = "books")
	@GetMapping
	public String query(@CacheParam(name = "token") @RequestParam String token) {
		return "success - " + token;
	}
}

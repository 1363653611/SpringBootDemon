package com.zbcn.duplicaterequest.controller;

import com.zbcn.duplicaterequest.annotation.CacheLock;
import com.zbcn.duplicaterequest.annotation.CacheParam;
import com.zbcn.duplicaterequest.annotation.LocalLock;
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

	/**
	 * redis 重复提交测试
	 * @param token
	 * @return
	 */
	@CacheLock(prefix = "books")
	@GetMapping
	public String redisQuery(@CacheParam(name = "token") @RequestParam String token) {
		return "success - " + token;
	}

	/**
	 * 基于 guava 重复提交测试
	 * @param token
	 * @return
	 */
	@LocalLock(key = "book:arg[0]")
	@GetMapping("/local")
	public String localQuery(@RequestParam String token) {
		return "success - " + token;
	}
}

package com.zbcn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常演示(默认配置):
 * 静态异常在 static/error 中
 * 动态异常页面在 templates/error 中
 */
@Controller
public class ErrorTestController {

	/**
	 * 动态异常演示
	 * @param model
	 * @return
	 */
	@RequestMapping("/error_5xx")
	public String error_5xx(Model model) {
		throw new RuntimeException("动态异常:运行异常测试");
	}
}

package com.zbcn.web.global;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * 全局数据绑定
 * 全局数据预处理
 */
@ControllerAdvice
public class MyControllerAdvice {

	/**
	 * 全局异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView customException(Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", e.getMessage());
		mv.setViewName("myerror");
		return mv;
	}

	/**
	 * 全局数据绑定
	 * @return
	 */
	@ModelAttribute(name = "md")
	public Map<String,Object> mydata() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("age", 99);
		map.put("gender", "男");
		return map;
	}

	/**
	 * 全局数据预处理
	 * @param binder
	 */
	@InitBinder("b")
	public void b(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("b.");
	}
	@InitBinder("a")
	public void a(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("a.");
	}
}

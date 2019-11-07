package com.zbcn.combootfilterlistenerinterceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @title CommonInterceptor
 *  @Description 定义拦截器
 *  @author zbcn8
 *  @Date 2019/11/7 15:18
 */
@Slf4j
public class CommonInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 1.preHandle在业务处理器处理请求之前被调用
	 * 2.postHandle在业务处理器处理请求执行完成后,生成视图之前执行
	 * 3.afterCompletion在DispatcherServlet完全处理完请求后被调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView)  throws Exception {
		log.error("请求ip:{}",request.getRemoteAddr());
		log.error("请求方法:{}",request.getMethod());
	}

}

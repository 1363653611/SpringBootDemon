package com.zbcn.shiroauthentication.component;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常捕获类
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
	@ExceptionHandler(value = AuthorizationException.class)
	public String handleAuthorizationException() {
		return "403";
	}
}

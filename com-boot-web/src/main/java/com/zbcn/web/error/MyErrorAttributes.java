package com.zbcn.web.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义异常处理类
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
		if ((Integer)map.get("status") == 500) {
			map.put("message", "服务器内部错误!");
		}
		return map;
	}
}

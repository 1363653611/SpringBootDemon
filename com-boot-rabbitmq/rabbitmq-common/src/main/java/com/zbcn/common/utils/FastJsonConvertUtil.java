package com.zbcn.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonConvertUtil {

	/**
	 * 字符串转换为实体类
	 * @param message
	 * @return
	 */
	public static  <T> T convertJSONToObject(String message,TypeReference<T> typeReference){
		return JSON.parseObject(message, typeReference);
	}

	/**
	 * 实体类转换为字符串
	 * @param obj
	 * @return
	 */
	public static String convertObjectToJSON(Object obj){
		String text = JSON.toJSONString(obj);
		return text;
	}
}

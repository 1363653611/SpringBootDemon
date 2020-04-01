package com.zbcn.combootmdc.util;

import com.zbcn.common.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 *  @title TraceIdUtil
 *  @Description mdc 记录工具类
 *  @author zbcn8
 *  @Date 2020/4/1 15:07
 */
public class TraceIdUtil {

	private static final String TRACE_ID = "traceId";

	private static final String DEFAULT_TRACE_ID = "default_" + UUIDUtils.getUid();

	public static void setTraceId(String traceId){
		traceId = StringUtils.isBlank(traceId)? DEFAULT_TRACE_ID : traceId;
		MDC.put(TRACE_ID, traceId);
	}

	public static String getTraceId() {
		//获取
		String traceId = MDC.get(TRACE_ID);
		//如果traceId为空，则返回默认值
		return StringUtils.isBlank(traceId) ? DEFAULT_TRACE_ID : traceId;
	}

	public static void removeTraceId(){
		MDC.remove(TRACE_ID);
	}

	//判断 traceId 是否为默认值
	public static Boolean defaultTraceId(String traceId) {
		return DEFAULT_TRACE_ID.equals(traceId);
	}

}

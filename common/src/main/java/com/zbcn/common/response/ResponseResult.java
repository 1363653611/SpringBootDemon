package com.zbcn.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {

	private static final long serialVersionUID = -4776089542458423462L;
	/**
	 * 返回状态码
	 */
	private Integer code;

	/**
	 * 返回状态
	 */
	private String status;

	/**
	 * 返回值信息
	 */
	private String msg;

	/**
	 * 返回数据
	 */
	private T data;

	private ResponseResult(Integer code, String status, String msg, T data) {
		super();
		this.code = code;
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * success 请求成功
	 * @param data 返回数
	 * @return
	 */
	public static <T> ResponseResult<T> success(T data){

		return new ResponseResult<T>(ApiStatus.OK.getCode(),ApiStatus.OK.getValue(), null, data);
	}

	/**
	 *  success 请求成功，无返回值
	 * @param msg 成功说明
	 * @return
	 */
	public static <T> ResponseResult<T> success(String msg){

		return new ResponseResult<T>(ApiStatus.OK.getCode(),ApiStatus.OK.getValue(), msg, null);
	}

	/**
	 * fail
	 * @param code 状态码
	 * @param status 状态值
	 * @param msg 失败原因
	 * @return
	 */
	public static <T> ResponseResult<T> fail(Integer code,String status,String msg){
		return new ResponseResult<T>(code, status, msg, null);
	}

	/**
	 * fail
	 * @param msg 失败原因
	 * @return
	 */
	public static <T> ResponseResult<T> fail(String msg){
		return fail(ApiStatus.BAD_REQUEST.getCode(),ApiStatus.BAD_REQUEST.getValue(),msg);
	}

	/**
	 * @Title: fail
	 * @Description: 失败原因，返回 exception 的报错信息
	 * @param msg
	 * @param e Exception
	 * @return
	 */
	public static <T> ResponseResult<T> fail(String msg,Exception e){
		return fail(msg + e);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseResult [code=" + code + ", status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

	public String toJsonString() {
		return JSON.toJSONString(this, new SerializerFeature[] {
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.UseISO8601DateFormat });
	}
}

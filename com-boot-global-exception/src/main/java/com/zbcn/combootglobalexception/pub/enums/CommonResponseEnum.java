package com.zbcn.combootglobalexception.pub.enums;

import com.zbcn.combootglobalexception.pub.interfaces.CommonExceptionAssert;
import com.zbcn.combootglobalexception.pub.exceptions.BaseException;
import com.zbcn.combootglobalexception.pub.response.BaseResponse;

/**
 *  通用异常枚举类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 15:29
 */
public enum CommonResponseEnum implements CommonExceptionAssert {
    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 服务器繁忙，请稍后重试
     */
    SERVER_BUSY(9998, "服务器繁忙"),
    /**
     * 服务器异常，无法识别的异常，尽可能对通过判断减少未定义异常抛出
     */
    SERVER_ERROR(9999, "网络异常"),

    NOT_DEFINED_EXCEPTION(8888,"未定义异常"),

    TOKEN_IS_INVALID(300,"token不合法"),
    TRY_OUT_TED_NOT_NULL(3000,"ted字段不能为空"),


    // Time
    DATE_NOT_NULL(5001, "日期不能为空"),
    DATETIME_NOT_NULL(5001, "时间不能为空"),
    TIME_NOT_NULL(5001, "时间不能为空"),
    DATE_PATTERN_MISMATCH(5002, "日期[%s]与格式[%s]不匹配，无法解析"),
    PATTERN_NOT_NULL(5003, "日期格式不能为空"),
    PATTERN_INVALID(5003, "日期格式[%s]无法识别"),
    ;

    private int code;

    private String message;

    CommonResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 校验返回结果是否成功
     * @param response 远程调用的响应
     */
    public static void assertSuccess(BaseResponse response) {
        SERVER_ERROR.assertNotNull(response);
        int code = response.getCode();
        if (CommonResponseEnum.SUCCESS.getCode() != code) {
            String msg = response.getMessage();
            throw new BaseException(code, msg);
        }
    }
}

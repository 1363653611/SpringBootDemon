package com.zbcn.combootglobalexception.pub.enums;

import com.zbcn.combootglobalexception.pub.interfaces.BusinessExceptionAssert;

/**
 *  响应枚举类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 14:52
 */
public enum  ResponseEnum  implements BusinessExceptionAssert {

    /**
     * Bad licence type
     */
    BAD_LICENCE_TYPE(7001, "Bad licence type."),
    /**
     * Licence not found
     */
    LICENCE_NOT_FOUND(7002, "Licence not found."),
    ;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    ResponseEnum(int code, String message) {
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
}

package com.zbcn.combootglobalexception.pub.enums;

import com.zbcn.combootglobalexception.pub.interfaces.CommonExceptionAssert;

/**
 *  参数校验异常枚举类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 15:28
 */
public enum  ArgumentResponseEnum implements CommonExceptionAssert {
    VALID_ERROR(0001,"参数校验异常");

    private int code;

    private String message;

    ArgumentResponseEnum(int code, String message) {
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

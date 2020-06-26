package com.zbcn.combootglobalexception.pub.exceptions;

import com.zbcn.combootglobalexception.pub.interfaces.IResponseEnum;

/**
 *  基本异常
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 13:47
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -4384729028602466830L;

    private Object[] args;

    private IResponseEnum responseEnum;


    public BaseException() {
    }

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.responseEnum = new IResponseEnum() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public IResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public Object[] getArgs() {
        return args;
    }
}

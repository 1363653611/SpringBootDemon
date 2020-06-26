package com.zbcn.combootglobalexception.pub.exceptions;

import com.zbcn.combootglobalexception.pub.interfaces.IResponseEnum;

/**
 *  <p>校验异常</p>
 *  <p>调用接口时，参数格式不合法可以抛出该异常</p>
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 16:50
 */
public class ValidationException extends  BaseException {


    private static final long serialVersionUID = 1L;

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
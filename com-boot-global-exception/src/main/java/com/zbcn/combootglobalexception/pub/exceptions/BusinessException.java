package com.zbcn.combootglobalexception.pub.exceptions;

import com.zbcn.combootglobalexception.pub.interfaces.IResponseEnum;

/**
 *  自定义业务异常，处理业务出现异常，可以抛出该异常
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 13:58
 */
public class BusinessException extends BaseException {



    public BusinessException(IResponseEnum responseEnum, Object[] args, String msg) {
        super(responseEnum, args, msg);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String msg,Throwable cause) {
        super(responseEnum,args, msg,cause);
    }

}

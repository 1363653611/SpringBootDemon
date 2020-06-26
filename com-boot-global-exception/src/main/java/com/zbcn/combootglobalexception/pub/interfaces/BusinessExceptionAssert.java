package com.zbcn.combootglobalexception.pub.interfaces;

import com.zbcn.combootglobalexception.pub.exceptions.BaseException;
import com.zbcn.combootglobalexception.pub.exceptions.BusinessException;

import java.text.MessageFormat;

/**
 *  业务异常断言
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 14:01
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    default BaseException newException(Object... args){
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this,args,msg);
    }

    default BaseException newException(Throwable t, Object... args){
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this,args,msg,t);
    }
}

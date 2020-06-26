package com.zbcn.combootglobalexception.pub.interfaces;

import com.zbcn.combootglobalexception.pub.exceptions.ArgumentException;
import com.zbcn.combootglobalexception.pub.exceptions.BaseException;

import java.text.MessageFormat;

/**
 *  公共的异常断言
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 16:47
 */
public interface CommonExceptionAssert extends IResponseEnum, Assert  {
    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg, t);
    }
}

package com.zbcn.combootglobalexception.pub.interfaces;

/**
 *  枚举方法 获取 code 和 message 框架接口
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 17:59
 */
public interface IResponseEnum {
    /**
     * 获取错误码
     * @return
     */
    int getCode();

    /**
     * 获取异常信息
     * @return
     */
    String getMessage();
}

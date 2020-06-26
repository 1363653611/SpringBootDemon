package com.zbcn.combootglobalexception.pub.response;

import java.io.Serializable;

/**
 *  错误响应
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 15:33
 */
public class ErrorResponse extends BaseResponse implements Serializable {
    private static final long serialVersionUID = -3039951708367697922L;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
       super(code,message);
    }


}

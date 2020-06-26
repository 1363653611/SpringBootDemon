package com.zbcn.combootglobalexception.pub.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *  通用返回结果，同{@link CommonResponse}
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 16:37
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class R<T> extends CommonResponse<T> {
    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.message = msg;
    }

    public R(Throwable e) {
        super();
        this.message = e.getMessage();
        this.code = -1;
    }
}

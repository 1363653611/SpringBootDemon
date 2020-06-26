package com.zbcn.combootglobalexception.pub.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  通用返回结果
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 16:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BaseResponse {
    /**
     * 数据列表
     */
    protected T data;

    public CommonResponse() {
        super();
    }

    public CommonResponse(T data) {
        super();
        this.data = data;
    }
}

package com.zbcn.combootglobalexception.pub.response;

/**
 *  返回分页查询结果集
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 16:35
 */
public class QueryDataResponse<T> extends CommonResponse<QueryData<T>> {
    public QueryDataResponse() {
    }

    public QueryDataResponse(QueryData<T> data) {
        super(data);
    }
}

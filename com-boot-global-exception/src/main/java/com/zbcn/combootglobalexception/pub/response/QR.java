package com.zbcn.combootglobalexception.pub.response;
/**
 *  响应信息主体，同{@link QueryDataResponse}
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 16:36
 */
public class QR<T> extends QueryDataResponse<T> {
    public QR() {
        super();
    }

    public QR(QueryData<T> data) {
        super(data);
    }
}

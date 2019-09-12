package com.zbcn;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ZbcnService.java
 * @Description 自定义service
 * @createTime 2019年09月07日 17:58:00
 */
public class ZbcnService {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String sayHello(){
        return "hello" + msg;
    }
}

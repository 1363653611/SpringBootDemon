package com.zbcn;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ZbcnServiceProperties.java
 * @Description 属性配置:提供参数
 * @createTime 2019年09月07日 17:51:00
 */
@ConfigurationProperties(prefix = "zbcn")
public class ZbcnServiceProperties {

    private static final String MSG = "hello";

    private String msg = MSG;

    public static String getMSG() {
        return MSG;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

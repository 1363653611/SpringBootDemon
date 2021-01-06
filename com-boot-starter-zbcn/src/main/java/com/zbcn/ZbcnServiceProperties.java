package com.zbcn;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ZbcnServiceProperties.java
 * @Description 属性配置:提供参数
 * @createTime 2019年09月07日 17:51:00
 */
@ConfigurationProperties(prefix = "zbcn")
//自动生成 spring-configuration-metadata 文件，代替默认
@PropertySource(value = {"classpath:META-INF/spring-configuration-metadata.json"},
        ignoreResourceNotFound = false, encoding = "UTF-8", name = "spring-configuration-metadata.json")
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

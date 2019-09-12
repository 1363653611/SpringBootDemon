package com.zbcn.bootbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName CfgProperties.java
 * @Description 已bean 的方式配置.避免每个都写@Value 指定
 * @createTime 2019年09月07日 11:23:00
 */
@Component
@ConfigurationProperties(prefix = "com")
@Data
public class CfgProperties {

    private String name;

    private Integer age;
}

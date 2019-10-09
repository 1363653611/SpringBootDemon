package com.zbcn.bootbase.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName PropertySourceConfig.java
 * @Description 配置方式1
 * @createTime 2019年09月07日 11:02:00
 */
@Component
@PropertySource("classpath:config/PropertyResource.properties")// 类似于:<context:property-placeholder location="classpath:book.properties"/>
@Data
public class PropertySourceConfig {

    /**
     * ${} 经过测试,会添加自动转化功能,否则.默认为String格式.
     */
    @Value("${com.name}")
    private String name;

    @Value("${com.age}")
    private Integer age;
}

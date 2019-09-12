package com.zbcn;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName Zbcn.java
 * @Description 自定义配置
 * @createTime 2019年09月07日 18:01:00
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZbcnServiceProperties.class)//使@ConfigurationProperties注解生效
@ConditionalOnClass(ZbcnService.class)//当某个类存在的时候自动配置这个类
public class ZbcnServiceAutoConfiguration {

    @Autowired
    private ZbcnServiceProperties zbcnServiceProperties;

    @Bean
    @ConditionalOnMissingBean(ZbcnService.class)
    public ZbcnService zbcnService(){
        ZbcnService zbcnService = new ZbcnService();
        zbcnService.setMsg(zbcnServiceProperties.getMsg());
        return zbcnService;
    }
}

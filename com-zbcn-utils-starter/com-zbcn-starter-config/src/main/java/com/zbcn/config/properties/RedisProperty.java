package com.zbcn.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  redis 的配置信息
 *  <br/>
 *  @author zbcn8
 *  @since  2021/1/13 18:52
 */
@ConfigurationProperties(prefix = "zbcn.redis")
@Data
public class RedisProperty {

    private String host;

    private String port;

    private String username;

    private String password;
}

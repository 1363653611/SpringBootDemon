package com.zbcn.config.config;

import com.zbcn.config.entity.ZbcnRedisConfigBean;
import com.zbcn.config.properties.RedisProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置信息
 *  <br/>
 *  @author zbcn8
 *  @since  2021/1/13 19:04
 */
@Configuration
@EnableConfigurationProperties(RedisProperty.class)
public class CustomRedisConfig {
    /**
     * redis 配置信息
     * @param redisProperty
     * @return
     */
    @Bean
    public ZbcnRedisConfigBean redisConfigBean(RedisProperty redisProperty){
        return new ZbcnRedisConfigBean(redisProperty.getHost(), redisProperty.getPort(),
                redisProperty.getUsername(), redisProperty.getPassword(),"自定义redis 配置");
    }
}

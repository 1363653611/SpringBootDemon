package com.zbcn.config.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  自定义配置信息
 *  <br/>
 *  @author zbcn8
 *  @since  2021/1/13 19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZbcnRedisConfigBean {

    private String host;

    private String port;

    private String username;

    private String password;

    private String desc = "自定义redis配置信息";
}

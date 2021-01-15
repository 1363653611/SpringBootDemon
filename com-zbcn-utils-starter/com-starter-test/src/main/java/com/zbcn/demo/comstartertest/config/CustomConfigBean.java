package com.zbcn.demo.comstartertest.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.zbcn.config.entity.ZbcnRedisConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CustomConfigBean {

    @Autowired
    private ZbcnRedisConfigBean redisConfigBean;

    @PostConstruct
    public void init(){
        redisConfig();
    }

    public void redisConfig(){
        System.out.println(JSONUtil.toJsonStr(redisConfigBean));
    }
}

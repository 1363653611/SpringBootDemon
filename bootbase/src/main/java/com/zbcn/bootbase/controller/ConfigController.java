package com.zbcn.bootbase.controller;

import com.zbcn.bootbase.config.CfgProperties;
import com.zbcn.bootbase.config.PropertySourceConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ConfigController.java
 * @Description TODO
 * @createTime 2019年09月07日 11:09:00
 */
@RestController
public class ConfigController {

    @Resource
    private PropertySourceConfig propertySourceConfig;

    @Resource
    private CfgProperties cfgProperties;

    @GetMapping("/index")
    public Object index(){

        return propertySourceConfig;
    }

    @GetMapping("/cfg")
    public Object cfg(){
        return cfgProperties;
    }
}

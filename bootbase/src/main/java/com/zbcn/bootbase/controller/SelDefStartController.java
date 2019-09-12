package com.zbcn.bootbase.controller;

import com.zbcn.ZbcnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName SelDefStartController.java
 * @Description 自定义springbootstart 的测试
 * @createTime 2019年09月07日 18:38:00
 */
@RestController
public class SelDefStartController {

    @Autowired
    private ZbcnService zbcnService;
    @GetMapping("self_start")
    public String selfStart(){

        return zbcnService.getMsg();

    }
}

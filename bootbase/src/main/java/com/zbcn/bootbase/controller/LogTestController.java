package com.zbcn.bootbase.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName LogTestController.java
 * @Description 日志测试
 * @createTime 2019年09月07日 11:58:00
 */
@RestController
@Slf4j
public class LogTestController {

    @GetMapping("/log")
    public void log(){
        log.error("测试日志...");
    }
}

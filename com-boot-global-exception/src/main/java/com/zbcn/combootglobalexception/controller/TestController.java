package com.zbcn.combootglobalexception.controller;

import com.zbcn.combootglobalexception.pub.response.R;
import com.zbcn.combootglobalexception.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  测试访问入口
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 17:11
 */
@RestController
@RequestMapping("/global/exception")
public class TestController {

    @Autowired
    private ITestService testService;

    @GetMapping("/test")
    public R test(@Validated @RequestParam String str){
        String test = testService.test(str);
        return new R(test);
    }
}

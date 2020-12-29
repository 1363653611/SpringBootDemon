package com.zbcn.zbcnauthentication.session.controller;

import com.zbcn.zbcnauthentication.session.service.BusiService;
import com.zbcn.zbcnauthentication.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class BusiController {

    @Autowired
    BusiService busiService;
    @GetMapping("/api")
    public String api(HttpSession session) {
        // 如果有登录就调用业务层执行业务逻辑，然后返回数据
        busiService.doSomeThing();
        return "成功返回数据";
    }

    @GetMapping("/api2")
    public String api2(HttpSession session) {
        // 如果有登录就调用业务层执行业务逻辑，然后返回数据
        return "成功返回数据";
    }
}

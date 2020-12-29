package com.zbcn.zbcnauthentication.jwt.controller;

import com.zbcn.zbcnauthentication.jwt.service.JwtBusiService;
import com.zbcn.zbcnauthentication.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jwt")
public class JwtBusiController {

    @Autowired
    private JwtBusiService jwtBusiService;

    @GetMapping("/api")
    public String api(HttpServletRequest request) {
        // 解析成功就执行业务逻辑返回数据
        jwtBusiService.doSomeThing();
        return "api成功返回数据";
    }

    @GetMapping("/api2")
    public String api2(HttpServletRequest request) {
        return "成功返回数据";
    }
}

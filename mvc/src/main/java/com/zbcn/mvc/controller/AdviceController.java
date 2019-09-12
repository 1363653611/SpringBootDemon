package com.zbcn.mvc.controller;

import com.zbcn.mvc.entity.DemonObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName AdviceController.java
 * @Description 测试@ControllerAdvice
 * @createTime 2019年08月31日 20:19:00
 */
@Controller
public class AdviceController {

    @RequestMapping("advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemonObj demonObj) throws Exception {
        throw new IllegalAccessException("抱歉,测试错误,来自:" + msg);
    }
}

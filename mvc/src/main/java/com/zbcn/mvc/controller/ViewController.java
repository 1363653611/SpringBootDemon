package com.zbcn.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName HelloController.java
 * @Description 页面跳转controller
 * @createTime 2019年08月31日 18:27:00
 */
@Controller
public class ViewController {

    @RequestMapping("/index")
    public String hello(){
        return "index";
    }

    /**
     * 上传页面
     * @return
     */
    @RequestMapping("/toUpload")
    public String toUpload(){
        return "/upload";
    }

    /**
     * 消息转换页面
     * @return
     */
    @RequestMapping("/toConvert")
    public String toConvert(){
        return "convert";
    }
}

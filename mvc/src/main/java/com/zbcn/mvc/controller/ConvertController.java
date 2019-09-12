package com.zbcn.mvc.controller;

import com.zbcn.mvc.entity.DemonObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ConvertController.java
 * @Description 消息转换测试
 * @createTime 2019年08月31日 21:50:00
 *
 */
@Controller
public class ConvertController {

    @RequestMapping(value = "convert",produces = {"application/x-zbcn"})
    @ResponseBody
    public DemonObj convert(@RequestBody DemonObj demonObj){

        return demonObj;
    }
}

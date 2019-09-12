package com.zbcn.mvc.controller.test;

import com.zbcn.mvc.service.DemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName NormalController.java
 * @Description 测试单元测试
 * @createTime 2019年09月01日 11:57:00
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private DemonService demonService;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
   // @ResponseBody
    public String testPage(Model model){

        return demonService.saySomething();
    }
}

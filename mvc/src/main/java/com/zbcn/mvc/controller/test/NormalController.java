package com.zbcn.mvc.controller.test;

import com.zbcn.mvc.service.DemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName NormalController.java
 * @Description 测试单元测试
 * @createTime 2019年09月01日 11:57:00
 */
@Controller
public class NormalController {

    @Autowired
    private DemonService demonService;

    @RequestMapping("/normal")
    public String testPage(Model model){
        model.addAttribute("msg",demonService.saySomething());

        return "page";
    }
}

package com.zbcn.mvc.controller;

import com.zbcn.mvc.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName AsyncController.java
 * @Description 异步支持
 * @createTime 2019年09月01日 10:56:00
 */
@Controller
public class AsyncController {

    @Autowired
    private PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall(){

        return pushService.getAsyncUpdate();
    }
}

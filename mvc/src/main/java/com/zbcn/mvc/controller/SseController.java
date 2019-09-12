package com.zbcn.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName sseController.java
 * @Description 服务器推送技术:需要流来器支持EventSource
 * @createTime 2019年09月01日 09:27:00
 */
@Controller
public class SseController {

    /**
     * 服务器推送：媒体类型：text/event-stream
     * @return
     */
    @RequestMapping(value = "push", produces = "text/event-stream;charset=utf-8")
    @ResponseBody
    public String push(){

        Random random = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Test 1,2,3" + random.nextInt() + "\n\n";
    }
}

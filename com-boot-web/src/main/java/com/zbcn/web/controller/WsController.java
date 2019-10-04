package com.zbcn.web.controller;

import com.zbcn.web.bean.message.ZbcnMessage;
import com.zbcn.web.bean.message.ZbcnResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * websocket 控制器类
 */
@Controller
public class WsController {

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public ZbcnResponse say(ZbcnMessage message) throws Exception {
		Thread.sleep(3000);
		return new ZbcnResponse("welcome, " + message.getName() + "!");
	}
}

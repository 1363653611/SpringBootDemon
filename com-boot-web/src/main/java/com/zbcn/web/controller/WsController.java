package com.zbcn.web.controller;

import com.zbcn.web.bean.message.ZbcnMessage;
import com.zbcn.web.bean.message.ZbcnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * websocket 控制器类
 */
@Controller
public class WsController {

	/**
	 * 广播消息
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public ZbcnResponse say(ZbcnMessage message) throws Exception {
		Thread.sleep(3000);
		return new ZbcnResponse("welcome, " + message.getName() + "!");
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	/**
	 * 点对点消息
	 * @param principal
	 * @param msg
	 */
	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg){

		if(principal.getName().equals("zbcn")){
			messagingTemplate.convertAndSendToUser("xy","/queue/notifications",principal.getName()+"-send:" + msg);
		}
		if(principal.getName().equals("xy")){
			messagingTemplate.convertAndSendToUser("zbcn","/queue/notifications",principal.getName()+"-send:" + msg);
		}
	}
}

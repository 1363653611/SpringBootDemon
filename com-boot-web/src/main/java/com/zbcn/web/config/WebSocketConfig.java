package com.zbcn.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker   //开启使用stomp协议来传输基于代理（message broker） 的消息
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * 注册stomp协议的，并映射指定的url
	 * @param registry
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry){
		registry.addEndpoint("endpointZbcn")
				.withSockJS();// 指定使用sockJs 协议
		registry.addEndpoint("/endpointChat").withSockJS();
	}

	/**
	 * 配置message 代理
	 * @param registry
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry){
		registry.enableSimpleBroker("queue","/topic");// 广播式应配置一个/topic 消息代理
	}
}

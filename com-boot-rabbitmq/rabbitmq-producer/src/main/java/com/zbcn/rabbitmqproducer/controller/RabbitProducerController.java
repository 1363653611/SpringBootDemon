package com.zbcn.rabbitmqproducer.controller;

import com.zbcn.common.entity.Order;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.common.utils.UUIDUtils;
import com.zbcn.rabbitmqproducer.service.RabbitProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rabbitmq/producer/send")
@RestController
public class RabbitProducerController {

	@Autowired
	private RabbitProducerService rabbitProducerService;

	/**
	 * 以发布/订阅的方式发布消息
	 * @return
	 */
	@GetMapping("/topic")
	public ResponseResult topicSend(){
		Order order= Order.builder().id(UUIDUtils.getUidInt()).messageId(UUIDUtils.getUid()).name(UUIDUtils.getUid()).build();
		rabbitProducerService.topicSend(order);
		return ResponseResult.success("发送成功.");
	}


	/**
	 * 以直接绑定队列的方式发布消息（消息发布到绑定的队列上）
	 * @return
	 */
	@GetMapping("/direct")
	public ResponseResult directSend(){
		Order order= Order.builder().id(UUIDUtils.getUidInt()).messageId(UUIDUtils.getUid()).name(UUIDUtils.getUid()).build();
		rabbitProducerService.topicSend(order);
		return ResponseResult.success("发送成功.");
	}
}

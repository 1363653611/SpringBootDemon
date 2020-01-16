package com.zbcn.rabbitmqproducer.controller;

import com.zbcn.common.entity.Order;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.common.utils.UUIDUtils;
import com.zbcn.rabbitmqproducer.service.RabbitProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rabbitmq/producer")
@RestController
public class RabbitProducerController {

	@Autowired
	private RabbitProducerService rabbitProducerService;

	@GetMapping("send")
	public ResponseResult send(){
		Order order= Order.builder().id(UUIDUtils.getUidInt()).messageId(UUIDUtils.getUid()).name(UUIDUtils.getUid()).build();
		rabbitProducerService.topicSend(order);
		return ResponseResult.success("发送成功.");
	}
}

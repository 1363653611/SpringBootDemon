package com.zbcn.rabbitmqproducer.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Rabbitmq 发送
 */
@Component
@Slf4j
public class RabbitSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 发送 message
	 * @param exchangeName 交换机名称
	 * @param routingKey 路由键
	 * @param t 数据
	 * @param correlationData 关联发布者 确认机制
	 * @param <T>
	 */
	public <T> void sendExchange(String exchangeName, String routingKey, T t, CorrelationData correlationData) {
		this.rabbitTemplate.convertAndSend(exchangeName, routingKey, t, correlationData);
	}
}

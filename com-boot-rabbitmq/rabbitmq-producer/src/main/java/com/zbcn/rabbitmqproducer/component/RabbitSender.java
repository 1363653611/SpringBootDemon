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
	 * @param exchangeName
	 * @param routingKey
	 * @param t
	 * @param correlationData
	 * @param <T>
	 */
	public <T> void sendExchange(String exchangeName, String routingKey, T t, CorrelationData correlationData) {
		this.rabbitTemplate.convertAndSend(exchangeName, routingKey, t, correlationData);
	}
}

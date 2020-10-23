package com.zbcn.rabbitmqproducer.config;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbcn.common.constant.StaticNumber;
import com.zbcn.common.mapper.BrokerMessageLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static com.zbcn.common.constant.StaticNumber.SUCCESSFUL_DELIVERY;


@Configuration
@Slf4j
public class TopicRabbitConfig {

	/**
	 * 队列1
	 * @return
	 */
	@Bean("queueMessage")
	public Queue queue(){
		//rounting-key 匹配规则, 是否开启持久化
		return new Queue(StaticNumber.ZBCN_TOPIC,true);
	}

	/**
	 * 队列2
	 * @return
	 */
	@Bean("queueMessages")
	public Queue queueMessages() {
		return new Queue(StaticNumber.YHJ_TOPIC,true);
	}

	/**
	 * 交换机
	 * @return
	 */
	@Bean("exchange")
	public TopicExchange exchange() {
		return ExchangeBuilder.topicExchange(StaticNumber.TOPIC_EXCHANGE).durable(true).build();
	}

	/**
	 * 绑定交换机
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(StaticNumber.ZBCN_TOPIC);
	}

	/**
	 * 绑定已 topic. 开头的所有队列
	 * @param queueMessages
	 * @param exchange
	 * @return
	 */
	@Bean
	public BindingBuilder.GenericArgumentsConfigurer bindingExchangeMessages(Queue queueMessages, Exchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with(StaticNumber.TOPIC);
	}

	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;

	/**
	 * 自定义template
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		// 消息是否成功发送到Exchange
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			if (ack) {
				log.info("消息成功发送到Exchange");
				String msgId = correlationData.getId();
				brokerMessageLogMapper.changeBrokerMessageLogStatus(msgId, SUCCESSFUL_DELIVERY, new Date());
			} else {
				log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
			}
		});
		// 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
		rabbitTemplate.setMandatory(true);
		// 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
		rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
			log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
		});
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		jsonObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(jsonObjectMapper);
		return jackson2JsonMessageConverter;
	}
}

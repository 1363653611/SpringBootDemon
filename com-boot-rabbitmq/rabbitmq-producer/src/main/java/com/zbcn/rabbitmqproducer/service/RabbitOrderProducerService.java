package com.zbcn.rabbitmqproducer.service;

import com.zbcn.common.constant.StaticNumber;
import com.zbcn.common.entity.BrokerMessageLog;
import com.zbcn.common.entity.Order;
import com.zbcn.common.mapper.BrokerMessageLogMapper;
import com.zbcn.common.mapper.OrderMapper;
import com.zbcn.common.utils.FastJsonConvertUtil;
import com.zbcn.common.utils.MsgBuildUtil;
import com.zbcn.rabbitmqproducer.component.RabbitSender;
import com.zbcn.rabbitmqproducer.pub.utils.MqUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class RabbitOrderProducerService {

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private BrokerMessageLogMapper brokerMessageLogMapper;
	@Autowired
	private RabbitSender rabbitSender;

	/**
	 * @Description 发送消息
	 * @param order
	 * @return void
	 */
	public void topicSend(Order order){
		orderMapper.insert(order);
		BrokerMessageLog brokerMessageLog = MsgBuildUtil.buildBrokerMsgLog(order);
		brokerMessageLogMapper.insertSelective(brokerMessageLog);
		//构建回调返回的数据
        CorrelationData correlationData = MqUtil.buildCorrelationData(order);
        rabbitSender.sendExchange(StaticNumber.TOPIC_EXCHANGE,StaticNumber.TOPIC_KEY,order,correlationData);
	}


	/**
	 * direct 直接方式发送消息
	 * @param order
	 */
	public void directSend(Order order) {
		orderMapper.insert(order);
		BrokerMessageLog brokerMessageLog = MsgBuildUtil.buildBrokerMsgLog(order);
		brokerMessageLogMapper.insertSelective(brokerMessageLog);
		//构建回调返回的数据
		CorrelationData correlationData = MqUtil.buildCorrelationData(order);
		rabbitSender.sendExchange(StaticNumber.DIRECT_EXCHANGE_NAME,StaticNumber.DIRECT_QUEUE_ROUTE_KEY,order,correlationData);
	}
}

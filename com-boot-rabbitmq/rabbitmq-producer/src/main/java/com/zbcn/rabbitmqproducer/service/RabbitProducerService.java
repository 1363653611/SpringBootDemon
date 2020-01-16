package com.zbcn.rabbitmqproducer.service;

import com.zbcn.common.constant.StaticNumber;
import com.zbcn.common.entity.BrokerMessageLog;
import com.zbcn.common.entity.Order;
import com.zbcn.common.mapper.BrokerMessageLogMapper;
import com.zbcn.common.mapper.OrderMapper;
import com.zbcn.common.utils.FastJsonConvertUtil;
import com.zbcn.rabbitmqproducer.component.RabbitSender;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class RabbitProducerService {

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private BrokerMessageLogMapper brokerMessageLogMapper;
	@Autowired
	private RabbitSender rabbitSender;

	/**
	 * @Description 发送消息
	 * @UserModule: exam-web-paper
	 * @author Dylan
	 * @date 2019/9/5
	 * @param order
	 * @return void
	 */
	public void topicSend(Order order){
		Date orderTime = new Date();
		orderMapper.insert(order);
		BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
		brokerMessageLog.setMessageId(order.getMessageId());
		brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
		// 设置消息状态为0 表示发送中
		brokerMessageLog.setStatus(StaticNumber.IN_DELIVERY);
		// 设置消息未确认超时时间窗口为 一分钟
		brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime,1));
		brokerMessageLog.setCreateTime(new Date());
		brokerMessageLog.setUpdateTime(new Date());
		brokerMessageLogMapper.insertSelective(brokerMessageLog);
		//构建回调返回的数据
		CorrelationData correlationData = new CorrelationData();
		correlationData.setId(order.getMessageId());
		rabbitSender.sendExchange(StaticNumber.TOPIC_EXCHANGE,StaticNumber.JAVAYOHO_TOPIC,order,correlationData);
	}
}

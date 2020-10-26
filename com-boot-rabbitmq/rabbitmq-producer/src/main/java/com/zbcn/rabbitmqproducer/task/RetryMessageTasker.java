package com.zbcn.rabbitmqproducer.task;

import com.alibaba.fastjson.TypeReference;
import com.zbcn.common.entity.BrokerMessageLog;
import com.zbcn.common.entity.Order;
import com.zbcn.common.mapper.BrokerMessageLogMapper;
import com.zbcn.common.utils.FastJsonConvertUtil;
import com.zbcn.rabbitmqproducer.service.RabbitOrderProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.zbcn.common.constant.StaticNumber.FAILURE_DELIVERY;

/**
 *  @title RetryMessageTasker
 *  @Description 重新发送定时任务
 *  @author zbcn8
 *  @Date 2020/1/15 11:47
 */
@Component
@Slf4j
public class RetryMessageTasker {

	@Autowired
	private RabbitOrderProducerService rabbitOrderProducerService;

	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;

	@Scheduled(initialDelay = 5000, fixedDelay = 15000)
	public void resend(){
		log.info("定时任务开始.");
		List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
		list.forEach(messageLog -> {
			if(messageLog.getTryCount() >= 3){
				//尝试次数超限后，更新为失败
				brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(), FAILURE_DELIVERY, new Date());
				log.info("尝试次数"+messageLog.getTryCount());
			} else {
				// 尝试次数更新
				brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(),  new Date());
				Order reSendOrder = FastJsonConvertUtil.convertJSONToObject(messageLog.getMessage(), new TypeReference<Order>() {
				});
				try {
					//重新发送消息
					rabbitOrderProducerService.topicSend(reSendOrder);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("异常信息" +e);
				}
			}
		});
	}
}

package com.zbcn.rabbitmqproducer.service.impl;

import com.zbcn.common.constant.StaticNumber;
import com.zbcn.common.entity.BrokerMessageLog;
import com.zbcn.common.entity.TSendMessage;
import com.zbcn.common.mapper.BrokerMessageLogMapper;
import com.zbcn.common.mapper.TSendMessageMapper;
import com.zbcn.common.utils.MsgBuildUtil;
import com.zbcn.common.utils.UUIDUtils;
import com.zbcn.rabbitmqproducer.component.RabbitSender;
import com.zbcn.rabbitmqproducer.pub.utils.MqUtil;
import com.zbcn.rabbitmqproducer.service.IMessageSendService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MessageSendServiceImpl implements IMessageSendService {
    @Resource
    private TSendMessageMapper sendMessageMapper;

    @Resource
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public String topicSend(TSendMessage sendMessage) {
        Assert.notNull(sendMessage, "要发布的消息不能为空。");
        sendMessage.setMessageType(StaticNumber.MessageType.TOPIC.getKey());
        recordMessage(sendMessage);
        CorrelationData correlationData = MqUtil.buildCorrelationData(sendMessage);
        rabbitSender.sendExchange(StaticNumber.TOPIC_EXCHANGE,StaticNumber.TOPIC_KEY,sendMessage,correlationData);
        return "topic 发送消息成功。";
    }

    /**
     * 数据库记录相关息
     * @param sendMessage
     */
    private void recordMessage(TSendMessage sendMessage) {
        String msgId = UUIDUtils.getUid();
        sendMessage.setCreateTime(new Date());
        sendMessage.setMessageId(msgId);
        //入库
        sendMessageMapper.insert(sendMessage);
        BrokerMessageLog brokerMessageLog = MsgBuildUtil.buildBrokerMsgLog(sendMessage);
        // 记录发送到 broker 的信息
        brokerMessageLogMapper.insertSelective(brokerMessageLog);
    }

    @Override
    public String directSend(TSendMessage sendMessage) {
        sendMessage.setMessageType(StaticNumber.MessageType.DIRECT.getKey());
        recordMessage(sendMessage);
        CorrelationData correlationData = MqUtil.buildCorrelationData(sendMessage);
        rabbitSender.sendExchange(StaticNumber.DIRECT_EXCHANGE_NAME,StaticNumber.DIRECT_QUEUE_ROUTE_KEY,sendMessage,correlationData);
        return "direct 消息发送成功";
    }

    @Override
    public String fanoutSend(TSendMessage sendMessage) {
        sendMessage.setMessageType(StaticNumber.MessageType.FANOUT.getKey());
        recordMessage(sendMessage);
        CorrelationData correlationData = MqUtil.buildCorrelationData(sendMessage);
        rabbitSender.sendExchange(StaticNumber.FANOUT_EXCHANGE_NAME,StaticNumber.FANOUT_QUEUE_ROUTE_KEY,sendMessage,correlationData);
        return "fanout 消息发送成功";
    }
}

package com.zbcn.rabbitmqconsumer.service;

import com.rabbitmq.client.Channel;
import com.zbcn.common.constant.StaticNumber;
import com.zbcn.common.entity.TSendMessage;
import com.zbcn.rabbitmqconsumer.util.HandleMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 *  topic 主题方式监听类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/26 14:39
 */
@Component
@Slf4j
public class TopicListenerService {

    @RabbitListener(queues = StaticNumber.ZBCN_TOPIC)
    @RabbitHandler
    public void topicListener1(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.ZBCN_TOPIC + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }

    @RabbitListener(queues = StaticNumber.YHJ_TOPIC)
    @RabbitHandler
    public void topicListener2(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.YHJ_TOPIC + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }

    @RabbitListener(queues = StaticNumber.OTHER_TOPIC)
    @RabbitHandler
    public void topicListener3(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.OTHER_TOPIC + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }

    @RabbitListener(queues = StaticNumber.OTHER_TOPIC_2)
    @RabbitHandler
    public void topicListener4(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.OTHER_TOPIC_2 + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }
}

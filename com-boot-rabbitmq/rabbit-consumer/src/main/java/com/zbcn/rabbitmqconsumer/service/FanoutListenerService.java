package com.zbcn.rabbitmqconsumer.service;

import com.rabbitmq.client.Channel;
import com.zbcn.common.constant.AckAction;
import com.zbcn.common.constant.StaticNumber;
import com.zbcn.common.entity.TSendMessage;
import com.zbcn.rabbitmqconsumer.util.HandleMessageUtil;
import com.zbcn.utils.message.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  广播模式监听类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/26 14:40
 */
@Component
@Slf4j
public class FanoutListenerService {


    @RabbitListener(queues = StaticNumber.FANOUT_QUEUE_NAME)
    @RabbitHandler
    public void fanoutListener1(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.FANOUT_QUEUE_NAME + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }

    @RabbitListener(queues = StaticNumber.FANOUT_QUEUE_NAME_2)
    @RabbitHandler
    public void fanoutListener2(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.FANOUT_QUEUE_NAME_2 + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }

    @RabbitListener(queues = StaticNumber.FANOUT_QUEUE_NAME_3)
    @RabbitHandler
    public void fanoutListener3(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.FANOUT_QUEUE_NAME_3 + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }




}

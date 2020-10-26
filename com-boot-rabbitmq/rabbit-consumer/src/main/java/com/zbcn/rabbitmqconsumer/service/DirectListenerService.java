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
 *  直接方式监听消息
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/26 15:24
 */
@Component
@Slf4j
public class DirectListenerService {

    @RabbitListener(queues = StaticNumber.DIRECT_QUEUE_NAME)
    @RabbitHandler
    public void directListener1(@Payload TSendMessage sendMessage, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info( StaticNumber.DIRECT_QUEUE_NAME + "开始处理消息");
        HandleMessageUtil.handleMessage(sendMessage, headers, channel);
    }
}

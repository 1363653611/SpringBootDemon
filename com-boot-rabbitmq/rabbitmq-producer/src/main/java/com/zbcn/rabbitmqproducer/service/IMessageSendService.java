package com.zbcn.rabbitmqproducer.service;

import com.zbcn.common.entity.TSendMessage;

/**
 *  消息发送service
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/22 14:18
 */
public interface IMessageSendService {

    /**
     * 发布订阅的方式发布消息
     * @param sendMessage
     * @return
     */
    String topicSend(TSendMessage sendMessage);
}

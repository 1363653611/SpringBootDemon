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
     * topic（主题方式）的方式发布消息
     * @param sendMessage
     * @return
     */
    String topicSend(TSendMessage sendMessage);

    /**
     * direct 直接的方式发布消息
     * @param sendMessage
     * @return
     */
    String directSend(TSendMessage sendMessage);


    /**
     * fanout 方式
     * @param sendMessage
     * @return
     */
    String fanoutSend(TSendMessage sendMessage);
}

package com.zbcn.rabbitmqproducer.pub.utils;

import com.zbcn.common.entity.Order;
import com.zbcn.common.entity.TSendMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 *  Mq 相关实体组装类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/22 15:00
 */
public class MqUtil {

    public static CorrelationData buildCorrelationData(Order order) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        return correlationData;
    }


    public static CorrelationData buildCorrelationData(TSendMessage sendMessage) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(sendMessage.getMessageId());
        return correlationData;
    }

}

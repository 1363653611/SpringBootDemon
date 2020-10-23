package com.zbcn.common.utils;

import com.zbcn.common.constant.StaticNumber;
import com.zbcn.common.entity.BrokerMessageLog;
import com.zbcn.common.entity.Order;
import com.zbcn.common.entity.TSendMessage;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 *  信息组装类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/22 14:34
 */
public class MsgBuildUtil {

    public static BrokerMessageLog buildBrokerMsgLog(Order order) {
        return getBrokerMessageLog(order.getMessageId(), FastJsonConvertUtil.convertObjectToJSON(order));
    }

    public static BrokerMessageLog buildBrokerMsgLog(TSendMessage message) {
        return getBrokerMessageLog(message.getMessageId(), FastJsonConvertUtil.convertObjectToJSON(message));
    }

    private static BrokerMessageLog getBrokerMessageLog(String messageId, String msg) {
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(messageId);
        brokerMessageLog.setMessage(msg);
        // 设置消息状态为0 表示发送中
        brokerMessageLog.setStatus(StaticNumber.IN_DELIVERY);
        // 设置消息未确认超时时间窗口为 一分钟
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(new Date(), 1));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        return brokerMessageLog;
    }

}

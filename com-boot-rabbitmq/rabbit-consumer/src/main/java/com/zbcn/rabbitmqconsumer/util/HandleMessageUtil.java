package com.zbcn.rabbitmqconsumer.util;

import com.rabbitmq.client.Channel;
import com.zbcn.common.constant.AckAction;
import com.zbcn.common.entity.TSendMessage;
import com.zbcn.utils.message.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  消息处理工具类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/26 14:51
 */
@Slf4j
public class HandleMessageUtil {

    /**
     * 队列消费次数
     */
    private static ThreadLocal<AtomicInteger> threadErrorTime = new ThreadLocal<>();

    /**
     * 处理消息公共方法
     * @param sendMessage
     * @param headers
     * @param channel
     * @throws IOException
     */
    public static void handleMessage(TSendMessage sendMessage, Map<String, Object> headers, Channel channel) throws IOException {
        /**
         * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
         * 以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
         * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
         */
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        /**
         *  multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
         *  如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
         */
        boolean multiple = false;


        /*应答模式*/
        String ackAction = AckAction.ACK_SUCCESSFUL;
        log.info("---------收到消息，开始消费---------");
        try {
            log.info("收到消息：【{}】", JsonUtils.writeValueAsString(sendMessage));
        } catch (IOException e) {
            log.error("消息消费失败。", e);
            AtomicInteger errorTimes = threadErrorTime.get();
            int i = errorTimes.incrementAndGet();
            ackAction = HandleMessageUtil.handleError(ackAction, e, i);
        } finally {
            HandleMessageUtil.handleMessageFinally(channel, deliveryTag, multiple, ackAction);
            threadErrorTime.remove();
        }
    }

    /**
     * 接收消息异常后的处理
     * @param ackAction
     * @param e
     * @param times
     * @return
     */
    public static String handleError(String ackAction, Exception e, Integer times) {
        //这里需要根据业务需求，看错误方式是否可以重新入队
        //需要考虑全面，否则会造成MQ阻塞，一直循环调用
        String message = e.getMessage();
        log.error(message,e);
        if(message == null|| times > 3){
            log.error("处理消息错误已超过三次，直接丢弃");
            // 直接拒绝，丢掉信息
            ackAction = AckAction.ACK_REJECT;
        }else {//重试
            ackAction = AckAction.ACK_RETRY;
        }

        return ackAction;
    }

    public static void handleMessageFinally(Channel channel, Long deliveryTag, boolean multiple, String ackAction) throws IOException {
        //消费失败被拒绝 应答模式
        //如果设置为true ，则会添加在队列的末端
        //channel.basicNack 消费失败重新入队 多条
        //channel.basicReject 消费失败重新入队 只能操作单条
        //channel.basicReject(deliveryTag,true);
        // 通过finally块来保证Ack/Nack会且只会执行一次
        if (ackAction == AckAction.ACK_SUCCESSFUL) {
            //ACK,确认一条消息已经被消费
            channel.basicAck(deliveryTag,multiple);
            log.info("发送消息被确认，最后处理。。");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error("睡眠被打断",e);
            }
        } else if (ackAction == AckAction.ACK_RETRY) {//重新加入队列
            channel.basicNack(deliveryTag, false, true);
        } else {//放弃入队，避免消息丢失，入库处理，后期可手动维护
            try {
                channel.basicNack(deliveryTag, false, false);
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

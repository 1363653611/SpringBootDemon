package com.zbcn.rabbitmqconsumer.service;

import com.rabbitmq.client.Channel;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.zbcn.common.constant.AckAction;
import com.zbcn.common.entity.ErrorAckMessage;
import com.zbcn.common.entity.Order;
import com.zbcn.common.entity.TSendMessage;
import com.zbcn.common.mapper.ErrorAckMessageDao;
import com.zbcn.common.utils.FastJsonConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static com.zbcn.common.constant.StaticNumber.*;

@Slf4j
@Component
@RabbitListener(queues = ZBCN_TOPIC)
public class OrderService {

	@Resource
	private ErrorAckMessageDao errorAckMessageDao;

	@Autowired
	private RedisTemplate redisTemplate;

	String  className = this.getClass().getName();

	/**
	 * 监听消息
	 *  "@Payload" 和 MessageConvert 一起使用，用来解析 Message 消息
	 * @param order
	 * @param headers
	 * @param channel
	 * @throws IOException
	 */
	@RabbitListener(queues = ZBCN_TOPIC)
	@RabbitHandler
	public void receiveMessage(@Payload Order order, @Headers Map<String,Object> headers, Channel channel) throws IOException {

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

		//消费者操作
		try {
			log.info("---------收到消息，开始消费---------");
			log.info("订单ID："+order.getId());
			String messageId = order.getMessageId();
			System.out.println("获取到的消息:" + messageId);

		} catch (Exception e) {
			ackAction = handleError(order, ackAction, e);

		}finally {
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
		/*
		 * “channel.basicQos(10)” 这个方法来设置当前channel的prefetch count。
		 * 也可以通过配置文件设置: spring.rabbitmq.listener.simple.prefetch=10
		 * RabbitMQ官方给出的建议是prefetch count一般设置在100 - 300之间。
		 * 也就是一个消费者服务最多接收到100 - 300个message来处理，允许处于unack状态。
		 * 这个状态下可以兼顾吞吐量也很高，同时也不容易造成内存溢出的问题。
		 */
//        channel.basicQos(150);
	}

	/**
	 * 接收消息异常后的处理
	 * @param object
	 * @param ackAction
	 * @param e
	 * @return
	 */
	private String handleError(Object object, String ackAction, Exception e) {
		//这里需要根据业务需求，看错误方式是否可以重新入队
		//需要考虑全面，否则会造成MQ阻塞，一直循环调用
		String message = e.getMessage();
		log.info(message);
		String msg = null;
		if(object instanceof Order){
			msg = ((Order)object).getMessageId();
		}
		if(object instanceof TSendMessage){
			msg = ((TSendMessage)object).getMessageId();
		}
		//添加尝试次数显示，达到最大限制次数，消费依旧失败，
		//不再进行尝试，存入库中，后期手动维护
		if(message == null){//直接入库
			ackAction = AckAction.ACK_REJECT;
			ErrorAckMessage errorAckMessage =
					ErrorAckMessage.builder().
							id(msg).
							errorMethod(className+"."+Thread.currentThread().getStackTrace()[1].getMethodName()).
							errorMessage(message).
							createTime(DateFormatUtils.format(new Date(),YMDHMS)).
							status(CONSUMER_FAILURE).
							message(FastJsonConvertUtil.convertObjectToJSON(object)).
							remarks("消费失败，为入队，请手动处理").
							build();
			errorAckMessageDao.insertAll(errorAckMessage);
		}
		if(message != null){//达到最大次数入库
			ackAction = AckAction.ACK_RETRY;
			//redis 来记录循环此申诉
			long incr = incr(msg, 1);
			//重复尝试入队三次，在此消费失败将放弃入队
			if(incr>3){
				ackAction = AckAction.ACK_REJECT;
				ErrorAckMessage errorAckMessage =
						ErrorAckMessage.builder().
								id(msg).
								errorMethod(className+"."+Thread.currentThread().getStackTrace()[1].getMethodName()).
								errorMessage(message).
								createTime(DateFormatUtils.format(new Date(),YMDHMS)).
								status(CONSUMER_TRY_FAILURE).
								message(FastJsonConvertUtil.convertObjectToJSON(object)).
								remarks("消费失败，入队尝试次数达到最大次数，请手动处理").
								build();
				errorAckMessageDao.insertAll(errorAckMessage);
			}
		}
		return ackAction;
	}

	/**
	 * 递增
	 *
	 * @param key   键
	 * @param delta 要增加几(大于0)
	 * @return
	 */
	public long incr(String key, long delta) {
		key = prefixKey(key);
		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	public static String prefixKey(String key){
		return PREFIXKEY + key;
	}
}

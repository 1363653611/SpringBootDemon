package com.zbcn.rabbitmqproducer.config;
import com.zbcn.common.constant.StaticNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  根据routing key，及通配规则，Topic exchange将分发到目标queue中。特点就是在它的路由键和绑定键之间是有规则的
 *
 *  1. 当一个队列的绑定键为 "#"（井号） 的时候，这个队列将会无视消息的路由键，接收所有的消息。(类似于 fanout 模式的规则)
 *  2. 当一个队列的绑定键binding_key 和 message 的routing_key 完全一致时，则绑定到 指定的队列（即和 direct 模式一致）
 *  3. routing_key为指定值， 当binding_key中包含（“#” 或者 “.”） 时，按照 相关规则匹配到能匹配的队列上
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/26 11:04
 */
@Configuration
@Slf4j
public class TopicRabbitConfig {


	/**
	 * 队列1
	 * @return
	 */
	@Bean
	public Queue queueMessageTopicYHJ() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(StaticNumber.YHJ_TOPIC,true);
	}

	/**
	 * 队列2
	 * @return
	 */
	@Bean
	public Queue queueMessageTopicZBCN() {
		return new Queue(StaticNumber.ZBCN_TOPIC,true);
	}

	/**
	 * 队列3
	 * @return
	 */
	@Bean
	public Queue queueMessageOther() {
		return new Queue(StaticNumber.OTHER_TOPIC,true);
	}

	/**
	 * 队列4
	 * @return
	 */
	@Bean
	public Queue queueMessageOther2() {
		return new Queue(StaticNumber.OTHER_TOPIC_2,true);
	}

	/**
	 * 交换机
	 * @return
	 */
	@Bean("exchange")
	public TopicExchange exchange() {
		return ExchangeBuilder.topicExchange(StaticNumber.TOPIC_EXCHANGE).durable(true).build();
	}

	/**
	 * 绑定交换机
	 * @param queueMessageOther
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeMessageOther(Queue queueMessageOther, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessageOther).to(exchange).with(StaticNumber.TOPIC_KEY);
	}

	/**
	 * 绑定交换机
	 * @param queueMessageOther2
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingExchangeMessageOther2(Queue queueMessageOther2, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessageOther2).to(exchange).with(StaticNumber.TOPIC_KEY);
	}


	/**
	 * 绑定已 topic. 开头的所有队列
	 * 将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
	 * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
	 * @param queueMessageTopicYHJ
	 * @param exchange
	 * @return
	 */
	@Bean
	public BindingBuilder.GenericArgumentsConfigurer bindingExchangeMessage(Queue queueMessageTopicYHJ, Exchange exchange) {
		return BindingBuilder.bind(queueMessageTopicYHJ).to(exchange).with(StaticNumber.TOPIC);
	}



}

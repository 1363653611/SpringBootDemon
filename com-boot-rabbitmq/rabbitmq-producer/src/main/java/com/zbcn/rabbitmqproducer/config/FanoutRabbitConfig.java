package com.zbcn.rabbitmqproducer.config;

import com.zbcn.common.constant.StaticNumber;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  fanout 扇区配置：将发布到交换机的信息，发布到绑定的全部队列
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/26 10:28
 */
@Configuration
public class FanoutRabbitConfig {
    /**
     * fanout 模式的队列1
     * @return
     */
    @Bean("fanoutQueue")
    public Queue queue(){
        //rounting-key 匹配规则, 是否开启持久化
        return new Queue(StaticNumber.FANOUT_QUEUE_NAME,true);
    }

    /**
     * fanout 模式的队列2
     * @return
     */
    @Bean("fanoutQueue2")
    public Queue queue2(){
        //rounting-key 匹配规则, 是否开启持久化
        return new Queue(StaticNumber.FANOUT_QUEUE_NAME_2,true);
    }

    /**
     * fanout 模式的队列3
     * @return
     */
    @Bean("fanoutQueue3")
    public Queue queue3(){
        //rounting-key 匹配规则, 是否开启持久化
        return new Queue(StaticNumber.FANOUT_QUEUE_NAME_3,true);
    }

    /**
     * 扇区交换机
     * @return
     */
    @Bean("fanoutExchange")
    public FanoutExchange exchange(){
        return ExchangeBuilder.fanoutExchange(StaticNumber.FANOUT_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 绑定队列1
     * @param fanoutQueue
     * @param fanoutExchange
     * @return
     */
    @Bean("fanoutBinding1")
    public Binding bindingExchangeMessage(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

    /**
     * 绑定队列2
     * @param fanoutQueue2
     * @param fanoutExchange
     * @return
     */
    @Bean("fanoutBinding2")
    public Binding bindingExchangeMessage2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    /**
     * 绑定队列2
     * @param fanoutQueue3
     * @param fanoutExchange
     * @return
     */
    @Bean("fanoutBinding3")
    public Binding bindingExchangeMessage3(Queue fanoutQueue3, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }
}

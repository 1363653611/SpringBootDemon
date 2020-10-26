package com.zbcn.rabbitmqproducer.config;

import com.zbcn.common.constant.StaticNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Message中的“routing key”如果和Binding中的“binding key”一致， Direct exchange则将message发到对应的queue中。
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/24 13:38
 */
@Configuration
@Slf4j
public class DirectRabbitConfig {

    /**
     * direct 模式的队列
     * @return
     */
    @Bean("directQueue")
    public Queue queue(){
        //rounting-key 匹配规则, 是否开启持久化
        return new Queue(StaticNumber.DIRECT_QUEUE_NAME,true);
    }

    /**
     * direct交换机
     * @return
     */
    @Bean("directExchange")
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(StaticNumber.DIRECT_EXCHANGE_NAME).durable(true).build();
    }


    /**
     * 绑定交换机
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean("directBinding")
    public Binding bindingExchangeMessage(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(StaticNumber.DIRECT_QUEUE_ROUTE_KEY);
    }



}

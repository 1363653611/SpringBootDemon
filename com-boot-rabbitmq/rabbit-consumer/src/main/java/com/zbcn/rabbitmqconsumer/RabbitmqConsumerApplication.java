package com.zbcn.rabbitmqconsumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.zbcn.*"})
@MapperScan(value = "com.zbcn.common.*")
@SpringBootApplication
public class RabbitmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqConsumerApplication.class, args);
	}

}

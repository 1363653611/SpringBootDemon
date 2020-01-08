package com.zbcn.combootjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zbcn.combootjob.quartz.mapper","com.zbcn.combootjob.schedule.dao"})
public class ComBootJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComBootJobApplication.class, args);
	}

}

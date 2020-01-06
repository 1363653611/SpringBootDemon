package com.zbcn.combootjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zbcn.combootjob.mapper")
public class ComBootJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComBootJobApplication.class, args);
	}

}

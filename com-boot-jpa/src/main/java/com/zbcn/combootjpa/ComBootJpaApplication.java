package com.zbcn.combootjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ComBootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComBootJpaApplication.class, args);
	}

}

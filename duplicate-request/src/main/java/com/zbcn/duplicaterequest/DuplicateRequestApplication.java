package com.zbcn.duplicaterequest;

import com.zbcn.duplicaterequest.generator.CacheKeyGenerator;
import com.zbcn.duplicaterequest.generator.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DuplicateRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuplicateRequestApplication.class, args);
	}

	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}

}

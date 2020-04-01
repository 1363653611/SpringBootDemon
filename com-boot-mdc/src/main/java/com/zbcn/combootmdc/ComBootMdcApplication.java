package com.zbcn.combootmdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //使用嵌入式容器时，可以使用@ServletComponentScan启用@WebServlet，@ WebFilter和@WebListener注释类的自动注册。
public class ComBootMdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComBootMdcApplication.class, args);
	}

}

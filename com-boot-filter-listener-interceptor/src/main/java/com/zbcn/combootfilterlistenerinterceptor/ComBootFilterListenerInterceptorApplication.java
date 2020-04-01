package com.zbcn.combootfilterlistenerinterceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//使用嵌入式容器时，可以使用@ServletComponentScan启用@WebServlet，@ WebFilter和@WebListener注释类的自动注册。
@ServletComponentScan
public class ComBootFilterListenerInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComBootFilterListenerInterceptorApplication.class, args);
	}

}

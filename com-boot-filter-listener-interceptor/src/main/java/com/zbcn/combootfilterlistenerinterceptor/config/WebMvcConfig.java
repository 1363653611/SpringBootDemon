package com.zbcn.combootfilterlistenerinterceptor.config;

import com.zbcn.combootfilterlistenerinterceptor.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 *  @title WebMvcConfig
 *  @Description WebMvcConfigurerAdapter 已经过时，继承 WebMvcConfigurationSupport 或者实现  WebMvcConfigurer
 *  @author zbcn8
 *  @Date 2019/11/7 19:32
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	@Bean
	public CommonInterceptor getCommonInterceptor(){
		return new CommonInterceptor();
	};
	// 增加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getCommonInterceptor());
	}

}

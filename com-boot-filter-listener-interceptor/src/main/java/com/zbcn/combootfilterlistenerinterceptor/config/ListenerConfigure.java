package com.zbcn.combootfilterlistenerinterceptor.config;

import com.zbcn.combootfilterlistenerinterceptor.listener.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  @title ListenerConfigure
 *  @Description 注入Listener
 *  @author zbcn8
 *  @Date 2019/11/7 19:57
 */
@Configuration
public class ListenerConfigure {

	@Bean
	public ServletListenerRegistrationBean<SessionListener> serssionListenerBean(){
		ServletListenerRegistrationBean<SessionListener>
				sessionListener = new ServletListenerRegistrationBean<SessionListener>(new SessionListener());
		return sessionListener;
	}
}

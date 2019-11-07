package com.zbcn.combootfilterlistenerinterceptor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextLIstener implements ServletContextListener {

	private static Logger LOG = LoggerFactory.getLogger(MyContextLIstener.class);
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOG.info("myListener 初始化...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		LOG.info("myListener 销毁...");
	}
}

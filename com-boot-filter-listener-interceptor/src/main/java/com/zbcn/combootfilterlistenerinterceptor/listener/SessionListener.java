package com.zbcn.combootfilterlistenerinterceptor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  @title SessionListener
 *  @Description SessionListener
 *  @author zbcn8
 *  @Date 2019/11/7 19:56
 */
public class SessionListener implements HttpSessionListener {

	private final static Logger LOG = LoggerFactory.getLogger(SessionListener.class);

	private AtomicInteger onLineCount = new AtomicInteger(0);

	public void sessionCreated(HttpSessionEvent event) {
		LOG.info("创建Session");
		event.getSession().getServletContext().setAttribute("onLineCount", onLineCount.incrementAndGet());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LOG.info("销毁Session");
		event.getSession().getServletContext().setAttribute("onLineCount", onLineCount.decrementAndGet());
	}
}

package com.zbcn.combootmdc.test;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.net.URL;

/**
 *  @title SimpleMDC
 *  @Description 测试一下MDC 的功能
 *  @author zbcn8
 *  @Date 2020/4/1 13:59
 */
public class SimpleMDC {

	public static void main(String[] args) {

		// You can put values in the MDC at any time. Before anything else
		// we put the first name
		MDC.put("first", "test");
		Logger logger = LoggerFactory.getLogger(SimpleMDC.class);

		// 硬编码 log 输入格式
		//programmaticConfiguration();
		//xml 文件配置
		configureViaXML_File();
		// We now put the last name
		MDC.put("last", "Parker");

		logger.info("Check enclosed.");

		logger.debug("The most beautiful two words in English.");

		MDC.put("first", "Richard");
		MDC.put("last", "Nixon");
		logger.info("I am not a crook.");
		logger.info("Attributed to the former US president. 17 Nov 1973.");

	}

	// 手动写入
	static void programmaticConfiguration(){
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.reset();
		PatternLayoutEncoder layout = new PatternLayoutEncoder();
		layout.setContext(loggerContext);
		layout.setPattern("%X{first} %X{last} - %m%n");
		layout.start();
		ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
		appender.setContext(loggerContext);
		appender.setEncoder(layout);
		appender.start();
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("root");
		root.addAppender(appender);
	}

	// 配置文件方式
	static void configureViaXML_File(){
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();
		URL url = Loader.getResourceBySelfClassLoader("test/simpleMDC.xml");
		try {
			configurator.doConfigure(url);
		} catch (JoranException e) {
			e.printStackTrace();
			StatusPrinter.print(lc);
		}
	}
}

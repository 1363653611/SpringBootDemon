package com.zbcn.mvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName WebInitializer.java
 * @Description WebApplicationInitializer 是spring 提供用来配置servlet 3.0+ 的配置接口。从而代替web.xml.
 * @createTime 2019年08月31日 18:15:00
 */
public class WebInitializer implements WebApplicationInitializer {


    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyConfig.class);
        //新建applicationContext，注册配置类，并和当前的servletContext关联
        context.setServletContext(servletContext);
        //注册spring MVC 的dispatcher
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
        dispatcher.setAsyncSupported(true);//开启异步支持
    }
}

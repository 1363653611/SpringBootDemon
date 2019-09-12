package com.zbcn.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName MyConfig.java
 * @Description Mvc配置类
 * @createTime 2019年08月31日 11:44:00
 */
@Configuration
@EnableWebMvc//开启springMVC的一些默认配置
@ComponentScan("com.zbcn.mvc.*")
@EnableScheduling//开启异步
public class MyConfig implements WebMvcConfigurer {

    /**
     * 添加视图解析
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");//编译后的页面位置
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    //添加静态资源映射
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")//外暴露的访问路径
                .addResourceLocations("classpath:/assets/");//文件的放置路径

    }

    /**
     * 添加拦截器
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    //注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor());
    }

    /**
     * 页面转向,集中配置
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        //欢迎页面
        registry.addViewController("/").setViewName("/index");
        //转向服务器推送浏览器的页面
        registry.addViewController("/sse").setViewName("/sse");
        //异步推送页面
        registry.addViewController("/async").setViewName("/async");
    }

    /**
     * 配置参数路径
     * @param configurer
     */
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //路径参数后面带点,则点后面的值会忽略.如:/xx.yy ,则yy 被忽略.
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 配置上传文件的bean
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * 信息转换类
     * @return
     */
    @Bean
    public MyMessageConvert messageConvert(){
        return new MyMessageConvert();
    }

    /**
     * 拓展messageconvert
     * @param converters
     */
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(messageConvert());
    }



}

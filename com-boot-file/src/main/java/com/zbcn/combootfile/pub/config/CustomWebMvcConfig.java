package com.zbcn.combootfile.pub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  跳转路径配置
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/1 14:27
 */
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册跳转页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("upload").setViewName("file_upload");
    }
}

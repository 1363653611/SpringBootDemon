package com.zbcn.zbcnauthentication.jwt.config;

import com.zbcn.zbcnauthentication.jwt.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  添加拦截器
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/29 16:32
 */
@Configuration
public class InterceptionConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor());
    }
}

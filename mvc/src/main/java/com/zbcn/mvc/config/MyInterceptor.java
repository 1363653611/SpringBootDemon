package com.zbcn.mvc.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName MyInterceptor.java
 * @Description 自定义拦截器: 通过实现HandlerInterceptor 或者继承 HandlerInterceptor 来自定义拦截器
 * @createTime 2019年08月31日 19:28:00
 */
public class MyInterceptor  implements HandlerInterceptor{

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
        long startTime = (Long)request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("本次请求的时间为:" + new Long(endTime - startTime) + "ms");
        request.setAttribute("handleTime",new Long(endTime - startTime));
    }
}

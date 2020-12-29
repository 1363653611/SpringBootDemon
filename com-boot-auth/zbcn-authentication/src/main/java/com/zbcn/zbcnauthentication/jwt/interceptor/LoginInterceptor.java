package com.zbcn.zbcnauthentication.jwt.interceptor;

import com.zbcn.zbcnauthentication.jwt.util.JwtUtil;
import com.zbcn.zbcnauthentication.jwt.util.UserContext;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 简单的白名单，登录这个接口直接放行
        if ("/jwt/login".equals(request.getRequestURI())) {
            System.out.println("白名单，不需要拦截");
            return true;
        }
        // 从请求头中获取token字符串并解析
        Claims claims = JwtUtil.parse(request.getHeader("Authorization"));
        // 已登录就直接放行
        if (claims != null) {
            // 将我们之前放到token中的userName给存到上下文对象中
            UserContext.add(claims.getSubject());
            System.out.println("已经登陆，直接放行");
            return true;
        }
        System.out.println("未登陆，请重新登陆");
        // 走到这里就代表是其他接口，且没有登录
        // 设置响应数据类型为json（前后端分离）
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 设置响应内容，结束请求
        out.write("请先登录");
        out.flush();
        out.close();
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束后要从上下文对象删除数据，如果不删除则可能会导致内存泄露
        UserContext.remove();
    }
}

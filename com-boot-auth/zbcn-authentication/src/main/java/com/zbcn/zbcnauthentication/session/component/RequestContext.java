package com.zbcn.zbcnauthentication.session.component;

import com.zbcn.zbcnauthentication.vo.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
 *  上下文对象
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/29 10:22
 */
public class RequestContext {

    public static HttpServletRequest getCurrentRequest(){
        // 通过`RequestContextHolder`获取当前request请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request;
    }

    public static User getCurrentUser() {
        // 通过request对象获取session对象，再获取当前用户对象
        return (User)getCurrentRequest().getSession().getAttribute("user");
    }
}

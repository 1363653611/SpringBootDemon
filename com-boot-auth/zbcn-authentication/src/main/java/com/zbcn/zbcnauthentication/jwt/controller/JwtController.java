package com.zbcn.zbcnauthentication.jwt.controller;


import com.zbcn.zbcnauthentication.jwt.util.JwtUtil;
import com.zbcn.zbcnauthentication.jwt.util.UserContext;
import com.zbcn.zbcnauthentication.vo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *  测试session 的controller
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/29 9:31
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {

    @PostMapping("/login")
    public String login(@RequestBody User user){
        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())){
            // 如果正确的话就返回生成的token（注意哦，这里服务端是没有存储任何东西的）
            return JwtUtil.generate(user.getUsername());
        }
        return "用户名或者密码错误";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 从请求头中获取token字符串
        String jwt = request.getHeader("Authorization");
        // 解析失败就提示用户登录
        if (JwtUtil.parse(jwt) == null) {
            return "请先登录";
        }
        UserContext.remove();
        return "退出成功";
    }
}

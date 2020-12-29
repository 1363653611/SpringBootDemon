package com.zbcn.zbcnauthentication.session.controller;


import com.zbcn.zbcnauthentication.vo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 *  测试session 的controller
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/29 9:31
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession httpSession){
        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())){
            //正确的话，将用户信息存储到session中
            httpSession.setAttribute("user", user);
            return "登录成功";
        }
        return "用户名或者密码错误";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 退出登录就是将用户信息删除
        session.removeAttribute("user");
        return "退出成功";
    }
}

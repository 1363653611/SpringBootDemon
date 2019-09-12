package com.zbcn.mvc.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName MyControllerAdvice.java
 * @Description 将控制器的全局配置放置在一个位置
 * @createTime 2019年08月31日 19:57:00
 */
@ControllerAdvice
public class MyControllerAdvice {
    /**
     * 全局的义常处理,拦截所有的异常
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        //跳转错误页面
        ModelAndView error = new ModelAndView("error");//错误页面
        error.addObject("errorMessage",exception.getMessage());
        return error;
    }

    /**
     * 所有注解了@RequestMapping 的方法可以获取到此值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","额外信息");
    }

    /**
     * 注解绑定数据
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }
}

package com.zbcn.mvc.controller;

import com.zbcn.mvc.entity.DemonObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName DemoRestController.java
 * @Description rest 分格
 * @createTime 2019年08月31日 18:58:00
 */
@RestController
@RequestMapping("rest")
public class DemoRestController {

    /**
     * 返回json格式数据
     * @param demonObj
     * @return
     */
    @RequestMapping(value = "/getJson",produces = {"application/json;charset=UTF-8"})
    public DemonObj getJson(DemonObj demonObj){
        return new DemonObj(demonObj.getId() +1,demonObj.getName()+"aaa");
    }

    /**
     * 返回xml格式数据
     * @param demonObj
     * @return
     */
    @RequestMapping(value = "/getXml",produces = {"application/xml;charset=UTF-8"})
    public DemonObj getXml(DemonObj demonObj){
        return new DemonObj(demonObj.getId() +1,demonObj.getName()+"aaa");
    }
}

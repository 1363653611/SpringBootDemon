package com.zbcn.zbcnauthentication.session.service;

import com.alibaba.fastjson.JSONObject;
import com.zbcn.zbcnauthentication.session.component.RequestContext;
import com.zbcn.zbcnauthentication.vo.User;
import org.springframework.stereotype.Service;

@Service
public class BusiService {

    public void doSomeThing(){
        User currentUser = RequestContext.getCurrentUser();
        System.out.println("处理业务：" + JSONObject.toJSONString(currentUser));
    }
}

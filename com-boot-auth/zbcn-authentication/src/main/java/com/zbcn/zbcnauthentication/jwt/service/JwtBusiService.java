package com.zbcn.zbcnauthentication.jwt.service;

import com.zbcn.zbcnauthentication.jwt.util.UserContext;
import org.springframework.stereotype.Service;

@Service
public class JwtBusiService {

    public void doSomeThing(){
        String currentUserName = UserContext.getCurrentUserName();
        System.out.println("处理业务：" + currentUserName);
    }
}

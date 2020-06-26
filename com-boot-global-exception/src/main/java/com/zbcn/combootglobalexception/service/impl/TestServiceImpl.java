package com.zbcn.combootglobalexception.service.impl;

import com.zbcn.combootglobalexception.pub.enums.ArgumentResponseEnum;
import com.zbcn.combootglobalexception.service.ITestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {


    @Override
    public String test(String str) {
        ArgumentResponseEnum.VALID_ERROR.assertNotEmpty(str);
        return null;
    }
}

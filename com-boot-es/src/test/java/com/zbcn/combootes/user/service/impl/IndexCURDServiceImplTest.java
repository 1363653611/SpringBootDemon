package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.IndexCURDService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class IndexCURDServiceImplTest {

    @Autowired
    private IndexCURDService indexCURDService;

    @Test
    void addDocument() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress("北京海淀");
        userInfo.setAge(25);
        userInfo.setBirthDate("2021-02-04");
        userInfo.setCreateTime(1612404326L);
        userInfo.setName("张鹏鹏");
        userInfo.setSalary("30000");
        userInfo.setRemark("谁知道");
        indexCURDService.addDocument(userInfo);
    }

    @Test
    void getDocument() {
        UserInfo document = indexCURDService.getDocument("WQXfancBP-7LnBQtUPRX");
        System.out.println(JSON.toJSONString(document));
    }

    @Test
    void updateDocument() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress("北京昌平");
        indexCURDService.updateDocument("WQXfancBP-7LnBQtUPRX",userInfo);
    }

    @Test
    void deleteDocument() {
        indexCURDService.deleteDocument("WQXfancBP-7LnBQtUPRX");
    }
}
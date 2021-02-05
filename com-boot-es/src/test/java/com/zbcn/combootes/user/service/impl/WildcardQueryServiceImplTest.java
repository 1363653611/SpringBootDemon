package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.WildcardQueryService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class WildcardQueryServiceImplTest {

    @Autowired
    private WildcardQueryService wildcardQueryService;

    @Test
    void wildcardQuery() {
        //查询所有以 “三” 结尾的姓名
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("name.keyword", "*三");
        List<UserInfo> userInfos = wildcardQueryService.wildcardQuery(wildcardQueryBuilder);
        System.out.println(JSONArray.toJSONString(userInfos));
    }
}
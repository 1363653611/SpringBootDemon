package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.TermQueryService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TermQueryServiceImplTest {

    @Autowired
    private TermQueryService termQueryService;

    @Test
    void termQuery() {
        TermQueryBuilder termQuery = QueryBuilders.termQuery("address.keyword", "北京市通州区");
        List<UserInfo> userInfos = termQueryService.termQuery(termQuery);
        System.out.println(JSONArray.toJSONString(userInfos));
    }

    @Test
    void termsQuery() {
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("address.keyword", "北京市丰台区", "北京市昌平区", "北京市大兴区");
        List<UserInfo> userInfos = termQueryService.termsQuery(termsQueryBuilder);
        System.out.println(JSONArray.toJSONString(userInfos));
    }
}
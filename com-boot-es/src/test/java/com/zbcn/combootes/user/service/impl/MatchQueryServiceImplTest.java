package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.MatchQueryService;
import org.elasticsearch.index.query.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MatchQueryServiceImplTest {

    @Autowired
    MatchQueryService queryService;

    /**
     *  匹配查询符合条件的所有数据，并设置分页
     */
    @Test
    void matchPageQuery() {
        // 构建查询条件
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        List<UserInfo> userInfos = queryService.matchPageQuery(matchAllQueryBuilder, 0, 10);
        System.out.println(JSONArray.toJSONString(userInfos));
    }

    /**
     * 匹配查询数据
     */
    @Test
    void matchQuery() {
        MatchQueryBuilder address = QueryBuilders.matchQuery("address", "*通州区");
        List<UserInfo> userInfos = queryService.matchQuery(address);
        System.out.println(JSONArray.toJSONString(userInfos));
    }

    /**
     * 词语匹配查询
     */
    @Test
    void matchPhraseQuery() {
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("address", "北京市通州区");
        List<UserInfo> userInfos = queryService.matchPhraseQuery(matchPhraseQueryBuilder);
        System.out.println(JSONArray.toJSONString(userInfos));
    }

    /**
     * 内容在多字段中进行查询
     */
    @Test
    void matchMultiQuery() {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("北京市", "address", "remark");
        List<UserInfo> userInfos = queryService.matchMultiQuery(multiMatchQueryBuilder);
        System.out.println(JSONArray.toJSONString(userInfos));
    }
}
package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.FuzzyQueryService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class FuzzyQueryServiceImplTest {

    @Autowired
    FuzzyQueryService fuzzyQueryService;

    @Test
    void fuzzyQuery() {
        FuzzyQueryBuilder fuzziness = QueryBuilders.fuzzyQuery("name", "三").fuzziness(Fuzziness.AUTO);
        List<UserInfo> userInfos = fuzzyQueryService.fuzzyQuery(fuzziness);
        System.out.println(JSONArray.toJSONString(userInfos));
    }
}
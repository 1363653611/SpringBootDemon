package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.RangeQueryService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RangeQueryServiceImplTest {

    @Autowired
    RangeQueryService rangeQueryService;

    @Test
    void rangeQuery() {
        //查询岁数 ≥ 30 岁的员工数据
        RangeQueryBuilder age = QueryBuilders.rangeQuery("age").gte(30);
        List<UserInfo> userInfos = rangeQueryService.rangeQuery(age);
        System.out.println(JSONArray.toJSONString(userInfos));

        System.out.println("第二次查询.");
        /**
         * 查询距离现在 30 年间的员工数据
         * [年(y)、月(M)、星期(w)、天(d)、小时(h)、分钟(m)、秒(s)]
         * 例如：
         * now-1h 查询一小时内范围
         * now-1d 查询一天内时间范围
         * now-1y 查询最近一年内的时间范围
         */
        // includeLower（是否包含下边界）、includeUpper（是否包含上边界）
        RangeQueryBuilder birthDate = QueryBuilders.rangeQuery("birthDate")
                .gte("now-30y").includeLower(true).includeUpper(true);
        List<UserInfo> userInfos1 = rangeQueryService.rangeQuery(birthDate);
        System.out.println(JSONArray.toJSONString(userInfos1));
    }
}
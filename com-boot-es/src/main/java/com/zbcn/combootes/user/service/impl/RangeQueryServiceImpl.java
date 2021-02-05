package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zbcn.combootes.user.constant.UserConstant;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.RangeQueryService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Slf4j
@Service
public class RangeQueryServiceImpl implements RangeQueryService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public List<UserInfo> rangeQuery(RangeQueryBuilder rangeQueryBuilder) {
        List<UserInfo> result = Lists.newArrayList();
        try {
            // 构建查询条件
            SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(rangeQueryBuilder);
            SearchRequest searchRequest = new SearchRequest(UserConstant.USER_INDEX);
            searchRequest.source(builder);
            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            // 根据状态和数据条数验证是否返回了数据
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits().value > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    UserInfo userInfo = JSON.parseObject(hit.getSourceAsString(), UserInfo.class);
                    result.add(userInfo);
                }
            }
        } catch (IOException e) {
            log.error("range 查询失败.",e);
        }
        return result;
    }
}

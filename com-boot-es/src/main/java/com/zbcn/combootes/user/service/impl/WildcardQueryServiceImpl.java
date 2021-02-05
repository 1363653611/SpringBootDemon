package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zbcn.combootes.user.constant.UserConstant;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.WildcardQueryService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class WildcardQueryServiceImpl implements WildcardQueryService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public List<UserInfo> wildcardQuery(WildcardQueryBuilder wildcardQueryBuilder) {
        List<UserInfo> result = Lists.newArrayList();
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(wildcardQueryBuilder);
            SearchRequest searchRequest = new SearchRequest(UserConstant.USER_INDEX);
            searchRequest.source(searchSourceBuilder);
            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            // 根据状态和数据条数验证是否返回了数据
            if (RestStatus.OK.equals(searchResponse.status())) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    UserInfo userInfo = JSON.parseObject(hit.getSourceAsString(), UserInfo.class);
                    result.add(userInfo);
                }
            }
        } catch (IOException e) {
            log.error("精确查询数据", e);
        }
        return result;
    }
}

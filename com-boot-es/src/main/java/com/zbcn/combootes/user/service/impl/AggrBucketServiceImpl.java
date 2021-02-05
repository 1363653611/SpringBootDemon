package com.zbcn.combootes.user.service.impl;

import com.zbcn.combootes.user.constant.UserConstant;
import com.zbcn.combootes.user.service.AggrBucketService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class AggrBucketServiceImpl implements AggrBucketService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public SearchResponse aggrBucket(AggregationBuilder aggr) {
        SearchResponse response = null;
        try {
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest(UserConstant.USER_INDEX);
            request.source(searchSourceBuilder);
            // 执行请求
            response = client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("bucket 聚合操作失败。", e);
        }
        return response;
    }
}

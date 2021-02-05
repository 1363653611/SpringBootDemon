package com.zbcn.combootes.user.service.impl;

import com.zbcn.combootes.user.constant.UserConstant;
import com.zbcn.combootes.user.service.AggrMetricService;
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

@Slf4j
@Service
public class AggrMetricServiceImpl implements AggrMetricService {

    @Autowired
    private RestHighLevelClient client;


    @Override
    public SearchResponse aggregationStats(AggregationBuilder aggr) {
        SearchResponse response = null;
        try {
            // 查询源构建器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggr);
            // 设置查询结果不返回，只返回聚合结果
            searchSourceBuilder.size(0);
            // 创建查询请求对象，将查询条件配置到其中
            SearchRequest request = new SearchRequest(UserConstant.USER_INDEX);
            request.source(searchSourceBuilder);
            // 执行请求
            response = client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

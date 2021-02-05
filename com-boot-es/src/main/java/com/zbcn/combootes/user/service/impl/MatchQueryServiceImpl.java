package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zbcn.combootes.user.constant.UserConstant;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.MatchQueryService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class MatchQueryServiceImpl implements MatchQueryService {

    @Autowired
    private RestHighLevelClient client;
    @Override
    public List<UserInfo> matchPageQuery(MatchAllQueryBuilder matchAllQueryBuilder,Integer page,Integer size) {
        List<UserInfo> result = Lists.newArrayList();
        try {
            // 创建查询源构造器
            SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(matchAllQueryBuilder);
            // 设置分页
            builder.from(page);
            builder.size(size);
            //设置排序
            builder.sort("salary", SortOrder.DESC);
            SearchRequest searchRequest = new SearchRequest(UserConstant.USER_INDEX);
            searchRequest.source(builder);
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
            log.error("match 查询 失败.", e);
        }
        return result;
    }

    @Override
    public List<UserInfo> matchQuery(MatchQueryBuilder matchQueryBuilder) {
        List<UserInfo> result = Lists.newArrayList();
        try {
            // 构建查询条件
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchQueryBuilder);
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest(UserConstant.USER_INDEX);
            searchRequest.source(searchSourceBuilder);
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
            log.error("match 查询 失败.", e);
        }

        return result;
    }

    @Override
    public List<UserInfo> matchPhraseQuery(MatchPhraseQueryBuilder matchPhraseQueryBuilder) {
        List<UserInfo> result = Lists.newArrayList();
        try {
            // 构建查询条件
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchPhraseQueryBuilder);
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest(UserConstant.USER_INDEX);
            searchRequest.source(searchSourceBuilder);
            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits().value > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    UserInfo userInfo = JSON.parseObject(hit.getSourceAsString(), UserInfo.class);
                    result.add(userInfo);
                }
            }
        } catch (IOException e) {
            log.error("match 查询 失败.", e);
        }
        return result;
    }

    @Override
    public List<UserInfo> matchMultiQuery(MultiMatchQueryBuilder multiMatchQueryBuilder) {
        List<UserInfo> result = Lists.newArrayList();
        try {
            // 构建查询条件
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(multiMatchQueryBuilder);
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest(UserConstant.USER_INDEX);
            searchRequest.source(searchSourceBuilder);
            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits().value > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    UserInfo userInfo = JSON.parseObject(hit.getSourceAsString(), UserInfo.class);
                    result.add(userInfo);
                }
            }
        } catch (IOException e) {
            log.error("match 查询 失败.", e);
        }
        return result;
    }
}

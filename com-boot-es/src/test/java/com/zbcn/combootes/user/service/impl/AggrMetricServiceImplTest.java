package com.zbcn.combootes.user.service.impl;

import com.zbcn.combootes.user.service.AggrMetricService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class AggrMetricServiceImplTest {

    @Autowired
    AggrMetricService aggrMetricService;
    @Test
    void aggregationStats() {
        StatsAggregationBuilder aggr = AggregationBuilders.stats("salary_stats").field("salary");
        SearchResponse response = aggrMetricService.aggregationStats(aggr);
        Aggregations aggregations = response.getAggregations();
        if (RestStatus.OK.equals(response.status()) || aggregations != null){
            ParsedStats aggregation = aggregations.get("salary_stats");
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            log.info("count：{}", aggregation.getCount());
            log.info("avg：{}", aggregation.getAvg());
            log.info("max：{}", aggregation.getMax());
            log.info("min：{}", aggregation.getMin());
            log.info("sum：{}", aggregation.getSum());
            log.info("-------------------------------------------");
        }
    }

    /**
     * min 统计员工工资最低值
     * @return
     */
    @Test
    public void aggregationMin(){
        MinAggregationBuilder min = AggregationBuilders.min("salary_min").field("salary");
        SearchResponse response = aggrMetricService.aggregationStats(min);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status()) || aggregations != null) {
            // 转换为 Min 对象
            ParsedMin aggregation = aggregations.get("salary_min");
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            log.info("min：{}", aggregation.getValue());
            log.info("-------------------------------------------");
        }
    }


    @Test
    public void aggregationMax(){
        // 设置聚合条件
        AggregationBuilder aggr = AggregationBuilders.max("salary_max").field("salary");
        SearchResponse response = aggrMetricService.aggregationStats(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status()) || aggregations != null) {
            // 转换为 Max 对象
            ParsedMax aggregation = aggregations.get("salary_max");
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            log.info("max：{}", aggregation.getValue());
            log.info("-------------------------------------------");
        }
    }

    /**
     * avg 统计员工工资平均值
     */
    @Test
    public void aggregationAvg(){
        // 设置聚合条件
        AggregationBuilder aggr = AggregationBuilders.avg("salary_avg").field("salary");
        SearchResponse response = aggrMetricService.aggregationStats(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status()) || aggregations != null) {
            // 转换为 Avg 对象
            ParsedAvg aggregation = aggregations.get("salary_avg");
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            log.info("avg：{}", aggregation.getValue());
            log.info("-------------------------------------------");
        }
    }

    @Test
    public void aggregationSum(){

        // 设置聚合条件
        SumAggregationBuilder aggr = AggregationBuilders.sum("salary_sum").field("salary");
        SearchResponse response = aggrMetricService.aggregationStats(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status()) || aggregations != null) {
            // 转换为 Sum 对象
            ParsedSum aggregation = aggregations.get("salary_sum");
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            log.info("sum：{}", String.valueOf((aggregation.getValue())));
            log.info("-------------------------------------------");
        }
    }

    /**
     * count 统计员工总数
     */
    @Test
    public void aggregationCount(){
        // 设置聚合条件
        AggregationBuilder aggr = AggregationBuilders.count("employee_count").field("salary");
        SearchResponse response = aggrMetricService.aggregationStats(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status()) || aggregations != null) {
            // 转换为 ValueCount 对象
            ParsedValueCount aggregation = aggregations.get("employee_count");
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            log.info("count：{}", aggregation.getValue());
            log.info("-------------------------------------------");
        }
    }

    @Test
    public void aggregationPercentiles(){
        // 设置聚合条件
        AggregationBuilder aggr = AggregationBuilders.percentiles("salary_percentiles").field("salary");
        SearchResponse response = aggrMetricService.aggregationStats(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status()) || aggregations != null) {
            // 转换为 Percentiles 对象
            ParsedPercentiles aggregation = aggregations.get("salary_percentiles");
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            for (Percentile percentile : aggregation) {
                log.info("百分位：{}：{}", percentile.getPercent(), percentile.getValue());
            }
            log.info("-------------------------------------------");
        }
    }

}
package com.zbcn.combootes.user.service.impl;

import com.zbcn.combootes.user.service.AggrBucketService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedTopHits;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class AggrBucketServiceImplTest {

    @Autowired
    AggrBucketService aggrBucketService;

    /**
     * 按岁数进行聚合分桶
     */
    @Test
    void  aggrBucketTerms() {
        TermsAggregationBuilder terms = AggregationBuilders.terms("age_bucket").field("age").size(10);
        SearchResponse response = aggrBucketService.aggrBucket(terms);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status())) {
            // 分桶
            Terms byCompanyAggregation = aggregations.get("age_bucket");
            List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
            // 输出各个桶的内容
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            for (Terms.Bucket bucket : buckets) {
                log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
            }
            log.info("-------------------------------------------");
        }
    }

    /**
     * 按工资范围进行聚合分桶
     */
    @Test
    public void aggrBucketRange(){
        AggregationBuilder aggr = AggregationBuilders.range("salary_range_bucket")
                .field("salary")
                .addUnboundedTo("低级员工", 3000)
                .addRange("中级员工", 5000, 9000)
                .addUnboundedFrom("高级员工", 9000);
        SearchResponse response = aggrBucketService.aggrBucket(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status())) {
            // 分桶
            Range byCompanyAggregation = aggregations.get("salary_range_bucket");
            List<? extends Range.Bucket> buckets = byCompanyAggregation.getBuckets();
            // 输出各个桶的内容
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            for (Range.Bucket bucket : buckets) {
                log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
            }
            log.info("-------------------------------------------");
        }
    }

    /**
     *  按照时间范围进行分桶
     */
    @Test
    public void aggrBucketDateRange() {
        AggregationBuilder aggr = AggregationBuilders.dateRange("date_range_bucket")
                .field("birthDate")
                .format("yyyy")
                .addRange("1985-1990", "1985", "1990")
                .addRange("1990-1995", "1990", "1995");
        SearchResponse response = aggrBucketService.aggrBucket(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status())) {
            // 分桶
            Range byCompanyAggregation = aggregations.get("date_range_bucket");
            List<? extends Range.Bucket> buckets = byCompanyAggregation.getBuckets();
            // 输出各个桶的内容
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            for (Range.Bucket bucket : buckets) {
                log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
            }
            log.info("-------------------------------------------");
        }
    }

    /**
     *  按工资多少进行聚合分桶
     */
    @Test
    public void aggrBucketHistogram(){
        AggregationBuilder aggr = AggregationBuilders.histogram("salary_histogram")
                .field("salary")
                .extendedBounds(0, 12000)
                .interval(3000);
        SearchResponse response = aggrBucketService.aggrBucket(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status())) {
            // 分桶
            Histogram byCompanyAggregation = aggregations.get("salary_histogram");
            List<? extends Histogram.Bucket> buckets = byCompanyAggregation.getBuckets();
            // 输出各个桶的内容
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            for (Histogram.Bucket bucket : buckets) {
                log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
            }
            log.info("-------------------------------------------");
        }
    }

    /**
     *  按出生日期进行分桶
     */
    @Test
    public void aggrBucketDateHistogram(){
        AggregationBuilder aggr = AggregationBuilders.dateHistogram("birthday_histogram")
                .field("birthDate")
                .calendarInterval(DateHistogramInterval.YEAR)
                .format("yyyy");
        SearchResponse response = aggrBucketService.aggrBucket(aggr);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status())) {
            // 分桶
            Histogram byCompanyAggregation = aggregations.get("birthday_histogram");

            List<? extends Histogram.Bucket> buckets = byCompanyAggregation.getBuckets();
            // 输出各个桶的内容
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            for (Histogram.Bucket bucket : buckets) {
                log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
            }
            log.info("-------------------------------------------");
        }
    }

    /**
     * topHits 按岁数分桶、然后统计每个员工工资最高值
     */
    @Test
    public void aggregationTopHits(){
        AggregationBuilder testTop = AggregationBuilders.topHits("salary_max_user")
                .size(1)
                .sort("salary", SortOrder.DESC);
        AggregationBuilder salaryBucket = AggregationBuilders.terms("salary_bucket")
                .field("age")
                .size(10);
        salaryBucket.subAggregation(testTop);
        SearchResponse response = aggrBucketService.aggrBucket(salaryBucket);
        // 获取响应中的聚合信息
        Aggregations aggregations = response.getAggregations();
        // 输出内容
        if (RestStatus.OK.equals(response.status())) {
            // 分桶
            Terms byCompanyAggregation = aggregations.get("salary_bucket");
            List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
            // 输出各个桶的内容
            log.info("-------------------------------------------");
            log.info("聚合信息:");
            for (Terms.Bucket bucket : buckets) {
                log.info("桶名：{}", bucket.getKeyAsString());
                ParsedTopHits topHits = bucket.getAggregations().get("salary_max_user");
                for (SearchHit hit:topHits.getHits()){
                    log.info(hit.getSourceAsString());
                }
            }
            log.info("-------------------------------------------");
        }
    }
}
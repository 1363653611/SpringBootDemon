package com.zbcn.combootes.user.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilder;

public interface AggrBucketService {

    /**
     * 分桶聚合操作
     * @param aggr
     * @return
     */
    SearchResponse aggrBucket(AggregationBuilder aggr);
}

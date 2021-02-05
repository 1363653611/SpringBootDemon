package com.zbcn.combootes.user.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilder;

/**
 *  聚合查询
 *  <br/>
 *  @author zbcn8
 *  @since  2021/2/5 11:16
 */
public interface AggrMetricService {

    /**
     * stats：聚合查询
     * @return
     */
    SearchResponse aggregationStats( AggregationBuilder aggr);
}

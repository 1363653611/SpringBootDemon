package com.zbcn.combootes.user.service;


import com.zbcn.combootes.user.entity.UserInfo;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

public interface BoolQueryService {

    /**
     * bool 查询
     * @param boolQueryBuilder
     * @return
     */
    List<UserInfo> boolQuery(BoolQueryBuilder boolQueryBuilder);
}

package com.zbcn.combootes.user.service;

import com.zbcn.combootes.user.entity.UserInfo;
import org.elasticsearch.index.query.RangeQueryBuilder;

import java.util.List;

/**
 *  区间查询service
 *  <br/>
 *  @author zbcn8
 *  @since  2021/2/4 15:45
 */
public interface RangeQueryService {

    /**
     * 区间查询
     * @param rangeQueryBuilder
     * @return
     */
    List<UserInfo> rangeQuery(RangeQueryBuilder rangeQueryBuilder);
}

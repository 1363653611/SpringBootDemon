package com.zbcn.combootes.user.service;

import com.zbcn.combootes.user.entity.UserInfo;
import org.elasticsearch.index.query.FuzzyQueryBuilder;

import java.util.List;

/**
 *  模块查询
 *  <br/>
 *  @author zbcn8
 *  @since  2021/2/4 15:25
 */
public interface FuzzyQueryService {

    /**
     * 模糊查询所有以 “三” 结尾的姓名
     * @param fuzzyQueryBuilder
     * @return
     */
    List<UserInfo> fuzzyQuery(FuzzyQueryBuilder fuzzyQueryBuilder);
}

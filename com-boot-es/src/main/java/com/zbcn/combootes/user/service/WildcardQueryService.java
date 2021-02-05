package com.zbcn.combootes.user.service;

import com.zbcn.combootes.user.entity.UserInfo;
import org.elasticsearch.index.query.WildcardQueryBuilder;

import java.util.List;

/**
 *  模糊查询
 *  <br/>
 *  @author zbcn8
 *  @since  2021/2/4 16:10
 */
public interface WildcardQueryService {


    /**
     * 模糊查询
     * *：表示多个字符（0个或多个字符）
     * ?：表示单个字符
     * @param wildcardQueryBuilder
     * @return
     */
    List<UserInfo> wildcardQuery(WildcardQueryBuilder wildcardQueryBuilder);

}

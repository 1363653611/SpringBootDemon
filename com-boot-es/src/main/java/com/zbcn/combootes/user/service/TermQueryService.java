package com.zbcn.combootes.user.service;

import com.zbcn.combootes.user.entity.UserInfo;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;

import java.util.List;

/**
 *  精确查询
 *  <br/>
 *  @author zbcn8
 *  @since  2021/2/4 11:02
 */
public interface TermQueryService {

    /**
     * 但条件精确查询
     * @param builder
     * @return
     */
    List<UserInfo> termQuery(TermQueryBuilder builder);

    /**
     * 多条件精确查询
     * @param termsBuilder
     * @return
     */
    List<UserInfo> termsQuery(TermsQueryBuilder termsBuilder);
}

package com.zbcn.combootes.user.service;

import com.zbcn.combootes.user.entity.UserInfo;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;

import java.util.List;

/**
 * 匹配查询
 * <br/>
 *
 * @author zbcn8
 * @since 2021/2/4 13:58
 */
public interface MatchQueryService {

    /**
     * match 查询:匹配查询符合条件的所有数据，并设置分页
     *
     * @param matchAllQueryBuilder
     * @return
     */
    List<UserInfo> matchPageQuery(MatchAllQueryBuilder matchAllQueryBuilder, Integer page, Integer size);


    /**
     * 匹配查询数据
     *
     * @return
     */
    List<UserInfo> matchQuery(MatchQueryBuilder matchQueryBuilder);

    /**
     * 词语匹配查询
     *
     * @param matchPhraseQueryBuilder
     * @return
     */
    List<UserInfo> matchPhraseQuery(MatchPhraseQueryBuilder matchPhraseQueryBuilder);

    /**
     * 内容在多字段中进行查询
     *
     * @param multiMatchQueryBuilder
     * @return
     */
    List<UserInfo> matchMultiQuery(MultiMatchQueryBuilder multiMatchQueryBuilder);
}

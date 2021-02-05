package com.zbcn.combootes.nba.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zbcn.combootes.nba.constant.NbaConst;
import com.zbcn.combootes.nba.dao.NBAPlayerDao;
import com.zbcn.combootes.nba.entity.NBAPlayer;
import com.zbcn.combootes.nba.service.NBAPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class NBAPlayerServiceImpl implements NBAPlayerService {

    @Resource
    private RestHighLevelClient client;

    @Resource
    private NBAPlayerDao nbaPlayerDao;

    @Override
    public boolean addPlayer(NBAPlayer player, String id) throws IOException {
        IndexRequest index = new IndexRequest("nba_latest").id(id).source(bean2Map(player));
        IndexResponse result = client.index(index, RequestOptions.DEFAULT);
        log.trace(JSON.toJSONString(result));
        return true;
    }

    @Override
    public NBAPlayer getPlayer(String id) throws IOException {
        GetRequest getRequest = new GetRequest(NbaConst.NBA_INDEX, id);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSource();
        NBAPlayer nbaPlayer = JSON.parseObject(JSON.toJSONString(source), NBAPlayer.class);
        log.trace(JSON.toJSONString(source));
        return nbaPlayer;
    }

    private static <T> Map<String,Object> bean2Map(T bean){
        HashMap<String, Object> map = new HashMap<>();
        if(Objects.nonNull(bean)){
            BeanMap beanMap = BeanMap.create(bean);
            for ( Object key: beanMap.keySet()){
                map.put(key+"",beanMap.get(String.valueOf(key)));
            }
        }
        return map;
    }

    public boolean importAll() throws IOException {
        List<NBAPlayer> nbaPlayers = nbaPlayerDao.selectAll();
        for(NBAPlayer nbaPlayer : nbaPlayers){
            addPlayer(nbaPlayer,String.valueOf(nbaPlayer.getId()));
        }
        return true;
    }

    @Override
    public boolean updatePlayer(NBAPlayer nbaPlayer, String id) throws IOException {
        UpdateRequest doc = new UpdateRequest(NbaConst.NBA_INDEX, id).doc(bean2Map(nbaPlayer));
        UpdateResponse update = client.update(doc, RequestOptions.DEFAULT);
        log.trace(JSON.toJSONString(update));
        return true;
    }

    @Override
    public boolean deletePlayer(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(NbaConst.NBA_INDEX, id);
        DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        log.trace(JSON.toJSONString(delete));
        return true;
    }

    @Override
    public boolean deleteAllPlayer() throws IOException {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(NbaConst.NBA_INDEX);
        BulkByScrollResponse delete = client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        log.trace(JSON.toJSONString(delete));
        return true;
    }

    @Override
    public List<NBAPlayer> searchMatch(String key, String value) throws IOException {
        SearchRequest searchRequest = new SearchRequest(NbaConst.NBA_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery(key,value));
        builder.from(0);
        builder.size(10000);
        searchRequest.source(builder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        log.trace(JSON.toJSONString(search));
        SearchHit[] hits = search.getHits().getHits();
        List<NBAPlayer> playerList = getNbaPlayers(hits);
        return playerList;
    }

    private List<NBAPlayer> getNbaPlayers(SearchHit[] hits) {
        List<NBAPlayer> playerList = Lists.newArrayList();
        for (SearchHit hit : hits){
            NBAPlayer nbaPlayer = JSONObject.parseObject(hit.getSourceAsString(), NBAPlayer.class);
            playerList.add(nbaPlayer);
        }
        return playerList;
    }

    @Override
    public List<NBAPlayer> searchTerm(String key, String value) throws IOException {
        SearchRequest searchRequest = new SearchRequest(NbaConst.NBA_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery(key, value));
        builder.from(0);
        builder.size(10000);
        searchRequest.source(builder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        log.trace(JSON.toJSONString(search));
        SearchHit[] hits = search.getHits().getHits();
        List<NBAPlayer> nbaPlayers = getNbaPlayers(hits);
        return nbaPlayers;
    }



}

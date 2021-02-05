package com.zbcn.combootes.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.zbcn.combootes.user.constant.UserConstant;
import com.zbcn.combootes.user.entity.UserInfo;
import com.zbcn.combootes.user.service.IndexCURDService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class IndexCURDServiceImpl implements IndexCURDService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public void addDocument(UserInfo userInfo) {
        try {
            IndexRequest indexRequest = new IndexRequest(UserConstant.USER_INDEX);
            // 将对象转换为 byte 数组
            byte[] bytes = JSON.toJSONBytes(userInfo);
            // 设置文档内容
            indexRequest.source(bytes, XContentType.JSON);
            // 执行增加文档
            IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
            log.info("创建状态：{}", response.status());
        } catch (IOException e) {
            log.info("创建失败.", e);
        }
    }

    @Override
    public UserInfo getDocument(String id) {
        UserInfo userInfo = null;
        try {
            GetRequest getRequest = new GetRequest(UserConstant.USER_INDEX,id);
            GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
            // 将 JSON 转换成对象
            if (response.isExists()) {
                userInfo = JSON.parseObject(response.getSourceAsBytes(), UserInfo.class);
                log.info("员工信息：{}", userInfo);
            }
        } catch (IOException e) {
            log.error("获取 用户信息失败.", e);
        }
        return userInfo;
    }

    @Override
    public void updateDocument(String id, UserInfo userInfo) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(UserConstant.USER_INDEX, id);
            // 将对象转换为 byte 数组
            byte[] json = JSON.toJSONBytes(userInfo);
            // 设置更新文档内容
            updateRequest.doc(json, XContentType.JSON);
            // 执行更新文档
            UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
            log.info("更新状态：{}", update.status());
        } catch (IOException e) {
            log.error("更新用户信息失败.", e);
        }
    }

    @Override
    public void deleteDocument(String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(UserConstant.USER_INDEX,id);
            DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("删除状态：{}", delete.status());
        } catch (IOException e) {
            log.error("删除文档失败.", e);
        }
    }
}

package com.zbcn.combootes.user.service;

import com.zbcn.combootes.user.entity.UserInfo;

import java.io.IOException;

/**
 *  索引增删改操作
 *  <br/>
 *  @author zbcn8
 *  @since  2021/2/3 17:04
 */
public interface IndexCURDService {

    /**
     * 添加用户信息
     * @param userInfo
     */
    void addDocument(UserInfo userInfo);

    /**
     * 获取用户信息
     */
    UserInfo getDocument(String id);

    /**
     * 更新文档
     */
    void updateDocument(String id, UserInfo userInfo);

    /**
     * 删除文档
     */
    void deleteDocument(String id);
}

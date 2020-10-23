package com.zbcn.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 节点
 */
@Data
public class Node implements Serializable {
    //域名
    private String domain;
    //ip
    private String ip;
    //数据
    private Map<String, Object> data;

    public Node(String domain, String ip) {
        this.domain = domain;
        this.ip = ip;
    }

    /**
     * 新增
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void put(String key, T value) {
        data.put(key, value);
    }

    /**
     * @Description 删除
     * @date 2019/9/27
     * @param key
     * @return void
     */
    public void remove(String key){
        data.remove(key);
    }

    /**
     * @Description 获取
     * @param key
     * @return T
     */
    public <T> T get(String key) {
        return (T) data.get(key);
    }
}

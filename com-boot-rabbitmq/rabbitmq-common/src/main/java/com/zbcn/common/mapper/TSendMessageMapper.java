package com.zbcn.common.mapper;

import com.zbcn.common.entity.TSendMessage;

public interface TSendMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSendMessage record);

    int insertSelective(TSendMessage record);

    TSendMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSendMessage record);

    int updateByPrimaryKey(TSendMessage record);
}
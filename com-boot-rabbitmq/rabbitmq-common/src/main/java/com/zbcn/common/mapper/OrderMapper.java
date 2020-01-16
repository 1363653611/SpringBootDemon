package com.zbcn.common.mapper;

import com.zbcn.common.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int insert(Order record);
    int deleteByPrimaryKey(Integer id);
    int insertSelective(Order record);
    Order selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Order record);
    int updateByPrimaryKey(Order record);
}

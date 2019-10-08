package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(String id);
}
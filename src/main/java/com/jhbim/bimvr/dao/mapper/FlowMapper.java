package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Flow;

public interface FlowMapper {
    int deleteByPrimaryKey(String id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);
}
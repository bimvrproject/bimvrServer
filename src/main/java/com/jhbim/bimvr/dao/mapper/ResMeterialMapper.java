package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ResMeterial;

public interface ResMeterialMapper {
    int deleteByPrimaryKey(Integer resMeterialId);

    int insert(ResMeterial record);

    int insertSelective(ResMeterial record);

    ResMeterial selectByPrimaryKey(Integer resMeterialId);

    int updateByPrimaryKeySelective(ResMeterial record);

    int updateByPrimaryKey(ResMeterial record);

    ResMeterial getOneRes(ResMeterial resMeterial);
}
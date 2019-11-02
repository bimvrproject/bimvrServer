package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.AppResMeterial;


public interface AppResMeterialMapper {
    int deleteByPrimaryKey(Integer resMeterialId);

    int insert(AppResMeterial record);

    int insertSelective(AppResMeterial record);

    AppResMeterial selectByPrimaryKey(Integer resMeterialId);

    int updateByPrimaryKeySelective(AppResMeterial record);

    int updateByPrimaryKey(AppResMeterial record);

    AppResMeterial getOneRes(AppResMeterial resMeterial);
}
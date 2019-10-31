package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.AppResModel;


public interface AppResModelMapper {
    int deleteByPrimaryKey(Integer resModelId);

    int insert(AppResModel record);

    int insertSelective(AppResModel record);

    AppResModel selectByPrimaryKey(Integer resModelId);

    int updateByPrimaryKeySelective(AppResModel record);

    int updateByPrimaryKey(AppResModel record);

    AppResModel getOneRes(AppResModel resModel);
}
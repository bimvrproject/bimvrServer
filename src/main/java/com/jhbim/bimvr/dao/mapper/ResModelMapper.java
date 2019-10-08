package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ResModel;
import org.apache.ibatis.annotations.Param;

public interface ResModelMapper {
    int deleteByPrimaryKey(Integer resModelId);

    int insert(ResModel record);

    int insertSelective(ResModel record);

    ResModel selectByPrimaryKey(Integer resModelId);

    int updateByPrimaryKeySelective(ResModel record);

    int updateByPrimaryKey(ResModel record);

    ResModel getOneRes(ResModel resModel);
    //根据项目id删除相关信息
    int deleteproject(Long projectid);
}
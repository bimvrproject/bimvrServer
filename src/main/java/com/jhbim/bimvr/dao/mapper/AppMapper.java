package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.App;
import com.jhbim.bimvr.dao.entity.pojo.Download;

import java.util.List;

public interface AppMapper {
    int deleteByPrimaryKey(Long id);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Long id);

    App selectByProjectId(Long projectId);

    List<App> select();

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}
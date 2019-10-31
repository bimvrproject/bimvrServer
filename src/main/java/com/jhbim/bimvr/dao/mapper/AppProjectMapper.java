package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.AppProject;
import com.jhbim.bimvr.dao.entity.pojo.Project;

import java.util.List;

public interface AppProjectMapper {
    int deleteByPrimaryKey(Long projectId);

    int insert(AppProject record);

    int insertSelective(AppProject record);

    List<AppProject> selectByPrimaryKey();

    AppProject selectByProjectId(Long projectId);

    int updateByPrimaryKeySelective(AppProject record);

    int updateByPrimaryKey(AppProject record);


}
package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Project;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long projectId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    //获取projectID
    List<Project> getProjectId(Long companyId);
    //获取project
    Project getProject(Long projectId);
}
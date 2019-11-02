package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.AppCompanyProject;

import java.util.List;

public interface AppCompanyProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppCompanyProject record);

    int insertSelective(AppCompanyProject record);

    AppCompanyProject selectByPrimaryKey(Long id);

    List<AppCompanyProject> select(Long companyId);

    int updateByPrimaryKeySelective(AppCompanyProject record);

    int updateByPrimaryKey(AppCompanyProject record);
}
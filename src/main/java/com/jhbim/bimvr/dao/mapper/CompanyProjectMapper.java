package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.CompanyProject;

public interface CompanyProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyProject record);

    int insertSelective(CompanyProject record);

    CompanyProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyProject record);

    int updateByPrimaryKey(CompanyProject record);
}
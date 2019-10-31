package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.AppProject;
import com.jhbim.bimvr.dao.entity.pojo.CompanyProject;

import java.util.List;

public interface CompanyProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyProject record);

    int insertSelective(CompanyProject record);

    CompanyProject selectByPrimaryKey(Long id);

    List<AppProject> selectByCompanyId(Long companyId);

    int updateByPrimaryKeySelective(CompanyProject record);

    int updateByPrimaryKey(CompanyProject record);

    //根据项目id删除该行内容
    int deleteprojectid(Long project);
    //根据项目id修改公司项目表
    int  updateprojectid(CompanyProject record);
}
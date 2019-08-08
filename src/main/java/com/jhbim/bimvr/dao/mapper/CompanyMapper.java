package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Long companyId);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long companyId);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}
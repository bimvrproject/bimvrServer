package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.MaterialSorts;

import java.util.List;

public interface MaterialSortsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialSorts record);

    int insertSelective(MaterialSorts record);

    MaterialSorts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaterialSorts record);

    int updateByPrimaryKey(MaterialSorts record);

    List<MaterialSorts> select();
}
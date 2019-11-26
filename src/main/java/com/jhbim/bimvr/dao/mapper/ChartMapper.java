package com.jhbim.bimvr.dao.mapper;

import com.github.pagehelper.Page;
import com.jhbim.bimvr.dao.entity.pojo.Chart;

public interface ChartMapper {
    int insert(Chart record);

    int insertSelective(Chart record);

    Page<Chart> findByPaging(String materialId);
}
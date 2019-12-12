package com.jhbim.bimvr.dao.mapper;

import com.github.pagehelper.Page;
import com.jhbim.bimvr.dao.entity.pojo.Furniture;


public interface FurnitureMapper {
    int insert(Furniture record);

    int insertSelective(Furniture record);


    Page<Furniture> findByFurn(String furniture_sortid);
}
package com.jhbim.bimvr.dao.mapper;

import com.github.pagehelper.Page;
import com.jhbim.bimvr.dao.entity.pojo.Material;


public interface MaterialMapper {
    int insert(Material record);

    int insertSelective(Material record);

    Page<Material> findByPaging(String material_sortid);
}
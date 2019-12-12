package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.FurnitureSorts;

import java.util.List;

public interface FurnitureSortsMapper {
    int insert(FurnitureSorts record);

    int insertSelective(FurnitureSorts record);

    List<FurnitureSorts> slelct();
}
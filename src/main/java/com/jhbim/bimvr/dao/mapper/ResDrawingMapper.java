package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ResDrawing;

public interface ResDrawingMapper {
    int deleteByPrimaryKey(Integer resPictureId);

    int insert(ResDrawing record);

    int insertSelective(ResDrawing record);

    ResDrawing selectByPrimaryKey(Integer resPictureId);

    int updateByPrimaryKeySelective(ResDrawing record);

    int updateByPrimaryKey(ResDrawing record);

    ResDrawing getOneRes(ResDrawing resDrawing);
}
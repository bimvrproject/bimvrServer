package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.AppResDrawing;
import com.jhbim.bimvr.dao.entity.pojo.ResDrawing;

public interface AppResDrawingMapper {
    int deleteByPrimaryKey(Integer resPictureId);

    int insert(AppResDrawing record);

    int insertSelective(AppResDrawing record);

    AppResDrawing selectByPrimaryKey(Integer resPictureId);

    int updateByPrimaryKeySelective(AppResDrawing record);

    int updateByPrimaryKey(AppResDrawing record);

    AppResDrawing getOneRes(AppResDrawing resDrawing);
}
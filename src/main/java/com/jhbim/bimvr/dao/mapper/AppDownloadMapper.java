package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.AppDownload;

import java.util.List;

public interface AppDownloadMapper {
    int deleteByPrimaryKey(Long id);

    Boolean insert(AppDownload appDownload);

    int insertSelective(AppDownload record);

    AppDownload selectByPrimaryKey(Long id);

    List<AppDownload> selectByUserId(String userId);

    int updateByPrimaryKeySelective(AppDownload record);

    int updateByPrimaryKey(AppDownload record);
}
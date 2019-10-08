package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Download;

import java.util.List;

public interface DownloadMapper {
    Boolean deleteByPrimaryKey(Long id);

    Boolean insert(Download download);

    int insertSelective(Download record);

    Download selectByPrimaryKey(Long id);

    List<Download> select();

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);
}
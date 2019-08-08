package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Version;

public interface VersionMapper {
    int insert(Version record);

    int insertSelective(Version record);
    Version checkVersion();

}
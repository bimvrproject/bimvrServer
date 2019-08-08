package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.Version;
import com.jhbim.bimvr.dao.mapper.VersionMapper;
import com.jhbim.bimvr.service.IVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class VersionServiceImpl implements IVersionService {
    @Resource
    VersionMapper versionMapper;
    /*
    *    checkVersion
    *    实际那着版本号 去查询整条version数据
    * */
    @Override
    public Version checkVersion() {
        Version reversion = versionMapper.checkVersion();
        return reversion;
    }
}

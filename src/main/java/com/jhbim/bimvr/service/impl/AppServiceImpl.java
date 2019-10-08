package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.App;
import com.jhbim.bimvr.dao.mapper.AppMapper;
import com.jhbim.bimvr.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-09-29 14:30
 */
@Service
public class AppServiceImpl implements IAppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public App selectByProjectId(Long projectId) {
        App app = appMapper.selectByProjectId(projectId);
        return app;
    }

    @Override
    public List<App> select() {
        return appMapper.select();
    }
}

package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.App;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-09-29 14:29
 */
public interface IAppService {

    App selectByProjectId(Long projectId);

    List<App> select();
}

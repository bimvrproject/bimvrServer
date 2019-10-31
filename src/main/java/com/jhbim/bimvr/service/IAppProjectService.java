package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.AppProject;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-10-30 15:13
 */
public interface IAppProjectService {

    //根据用户id查询所属项目
    List<AppProject> select();
}

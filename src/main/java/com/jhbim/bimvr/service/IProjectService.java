package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.User;

import java.util.List;

public interface IProjectService {
    //根据用户名查询所属项目
    List<Project> getProject(User userName);
}

package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {
    public final static Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Resource
    ProjectMapper projectMapper;
    /*
    *  用户获取
    *      通过  公司id查询所属项目
    *      两个步骤:1 根据用户名查询公司名下所有项目项目
    *               2 通过项目id查询每个项目详细信息
    * */
    @Override
    public List<Project> getProject(User username) {
        Long companyId = username.getCompanyId();
        List<Project> projectIdList =  projectMapper.getProjectId(companyId);
        List<Project> projectList = new ArrayList<>();
        for (int i = 0; i < projectIdList.size(); i++) {
            Long projectId = projectIdList.get(i).getProjectId();
            Project project = projectMapper.getProject(projectId);
            projectList.add(project);
        }
        return projectList;
    }
}

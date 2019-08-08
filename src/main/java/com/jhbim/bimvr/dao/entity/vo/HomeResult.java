package com.jhbim.bimvr.dao.entity.vo;

import com.jhbim.bimvr.dao.entity.pojo.Project;

import java.util.List;

/*
*    home页面返回值
* */
public class HomeResult {
    private String username;//返回用户名
    private List<Project> projectList;//返回用户所属项目
    private Long companyId;//公司名称

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "HomeResult{" +
                "username='" + username + '\'' +
                ", projectList=" + projectList +
                ", companyId=" + companyId +
                '}';
    }
}

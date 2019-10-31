package com.jhbim.bimvr.dao.entity.vo.app;

import com.jhbim.bimvr.dao.entity.pojo.AppProject;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-10-31 10:46
 */
public class AppHomeResult {
    private String username;//返回用户名
    private List<AppProject> appProjectList;//项目详细信息 app
    private Long companyId;//公司名称

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AppProject> getAppProjectList() {
        return appProjectList;
    }

    public void setAppProjectList(List<AppProject> appProjectList) {
        this.appProjectList = appProjectList;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

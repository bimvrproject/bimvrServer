package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.AppCompanyProject;
import com.jhbim.bimvr.dao.entity.pojo.AppProject;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.mapper.AppCompanyProjectMapper;
import com.jhbim.bimvr.dao.mapper.AppProjectMapper;
import com.jhbim.bimvr.dao.mapper.CompanyProjectMapper;
import com.jhbim.bimvr.service.IAppProjectService;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shuihu
 * @create 2019-10-30 15:15
 */

@Service
public class AppProjectServiceImpl implements IAppProjectService {
    public final static Logger log = LoggerFactory.getLogger(AppProjectServiceImpl.class);
    @Autowired
    private AppProjectMapper appProjectMapper;

    @Autowired
    private AppCompanyProjectMapper appCompanyProjectMapper;

    @Override
    public List<AppProject> select() {
        User user = ShiroUtil.getUser();
        List<AppProject> appProjectList = appProjectMapper.selectByPrimaryKey();
        List<AppCompanyProject> selects = appCompanyProjectMapper.select(user.getCompanyId());
        List<AppProject> list = new ArrayList<>();

        selects.forEach(select->{
            AppProject appProject = appProjectMapper.selectByProjectId(select.getProjectId());
            list.add(appProject);

        });
        return list;
    }
}

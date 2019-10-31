package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.AppResMeterial;

import com.jhbim.bimvr.dao.mapper.AppResMeterialMapper;

import com.jhbim.bimvr.service.IAppResMeterialService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppResMeterialServiceImpl implements IAppResMeterialService {

    @Value("${server.project.baseUrl}")
    private String serverProjectBaseUrl;

    @Resource
    AppResMeterialMapper appResMeterialMapper;

    @Override
    public AppResMeterial getOneRes(Long companyId, Long projectId, String modelId) {
        AppResMeterial resMeterial = new AppResMeterial();
        resMeterial.setCompanyId(companyId);
        resMeterial.setProjectId(projectId);
        resMeterial.setModelId(modelId);
        resMeterial = appResMeterialMapper.getOneRes(resMeterial);
        resMeterial.setUrl(serverProjectBaseUrl + resMeterial.getUrl());
        return resMeterial;
    }
}

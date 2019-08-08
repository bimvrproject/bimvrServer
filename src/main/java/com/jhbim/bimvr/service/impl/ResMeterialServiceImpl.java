package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.ResMeterial;
import com.jhbim.bimvr.dao.mapper.ResMeterialMapper;
import com.jhbim.bimvr.service.IResMeterialService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResMeterialServiceImpl implements IResMeterialService {

    @Value("${server.project.baseUrl}")
    private String serverProjectBaseUrl;

    @Resource
    ResMeterialMapper resMeterialMapper;

    @Override
    public ResMeterial getOneRes(Long companyId, Long projectId, String modelId) {
        ResMeterial resMeterial = new ResMeterial();
        resMeterial.setCompanyId(companyId);
        resMeterial.setProjectId(projectId);
        resMeterial.setModelId(modelId);
        resMeterial = resMeterialMapper.getOneRes(resMeterial);
        resMeterial.setUrl(serverProjectBaseUrl + resMeterial.getUrl());
        return resMeterial;
    }
}

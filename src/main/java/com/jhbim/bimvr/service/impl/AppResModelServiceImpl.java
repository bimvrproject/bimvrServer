package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.AppResModel;

import com.jhbim.bimvr.dao.mapper.AppResModelMapper;

import com.jhbim.bimvr.service.IAppResModelService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppResModelServiceImpl implements IAppResModelService {

    @Value("${server.project.baseUrl}")
    private String serverProjectBaseUrl;

    @Resource
    AppResModelMapper appResModelMapper;

    @Override
    public AppResModel getOneRes(Long companyId, Long projectId, String modelId) {
        AppResModel resModel = new AppResModel();
        if (companyId !=null && projectId != null && modelId !=null){
            resModel.setCompanyId(companyId);
            resModel.setProjectId(projectId);
            resModel.setModelId(modelId);

            resModel = appResModelMapper.getOneRes(resModel);
            resModel.setUrl(serverProjectBaseUrl + resModel.getUrl());
            return resModel;
        }

        return null;
    }
}

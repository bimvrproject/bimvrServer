package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.ResModel;
import com.jhbim.bimvr.dao.mapper.ResModelMapper;
import com.jhbim.bimvr.service.IResModelService;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResModelServiceImpl implements IResModelService {

    @Value("${server.project.baseUrl}")
    private String serverProjectBaseUrl;

    @Resource
    ResModelMapper resModelMapper;

    @Override
    public ResModel getOneRes(Long companyId, Long projectId, String modelId) {
        ResModel resModel = new ResModel();
        if (companyId !=null && projectId != null && modelId !=null){
            resModel.setCompanyId(companyId);
            resModel.setProjectId(projectId);
            resModel.setModelId(modelId);

            resModel = resModelMapper.getOneRes(resModel);
            resModel.setUrl(serverProjectBaseUrl + resModel.getUrl());
            return resModel;
        }

        return null;
    }
}

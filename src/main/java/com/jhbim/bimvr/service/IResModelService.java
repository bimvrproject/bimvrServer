package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.ResModel;

public interface IResModelService {
    ResModel getOneRes(Long companyId, Long projectId, String modelId);
}

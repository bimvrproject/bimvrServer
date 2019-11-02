package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.AppResModel;


public interface IAppResModelService {
    AppResModel getOneRes(Long companyId, Long projectId, String modelId);
}

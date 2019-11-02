package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.AppResMeterial;


public interface IAppResMeterialService {


    AppResMeterial getOneRes(Long companyId, Long projectId, String modelId);
}

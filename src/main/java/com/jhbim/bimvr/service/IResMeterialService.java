package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.ResMeterial;

public interface IResMeterialService {


    ResMeterial getOneRes(Long companyId, Long projectId, String modelId);
}

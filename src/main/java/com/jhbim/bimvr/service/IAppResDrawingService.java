package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.AppResDrawing;


public interface IAppResDrawingService {
    AppResDrawing getOneRes(Long companyId, Long projectId, String modelId);
}

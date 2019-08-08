package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.ResDrawing;

public interface IResDrawingService {
    ResDrawing getOneRes(Long companyId, Long projectId, String modelId);
}

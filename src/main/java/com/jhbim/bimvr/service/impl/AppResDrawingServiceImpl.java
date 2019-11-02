package com.jhbim.bimvr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jhbim.bimvr.dao.entity.pojo.AppResDrawing;
import com.jhbim.bimvr.dao.entity.vo.ResDrawingVO;
import com.jhbim.bimvr.dao.mapper.AppResDrawingMapper;

import com.jhbim.bimvr.service.IAppResDrawingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppResDrawingServiceImpl implements IAppResDrawingService {

    @Value("${server.project.baseUrl}")
    private String serverProjectBaseUrl;

    @Resource
    AppResDrawingMapper appResDrawingMapper;

    @Override
    public AppResDrawing getOneRes(Long companyId, Long projectId, String modelId) {
        AppResDrawing resDrawing = new AppResDrawing();
        resDrawing.setCompanyId(companyId);
        resDrawing.setProjectId(projectId);
        resDrawing.setModelId(modelId);
        resDrawing = appResDrawingMapper.getOneRes(resDrawing);
        // 将资源json字段转换为对象
        List<ResDrawingVO> resDrawingList = JSON.parseObject(resDrawing.getUrl(), new TypeReference<ArrayList<ResDrawingVO>>() {});
        // 为每个obj.res.resUrl增加Url前缀
        for (ResDrawingVO resDrawingVO: resDrawingList){
            resDrawingVO.setResUrl(serverProjectBaseUrl);
        }
        resDrawing.setUrl(JSON.toJSONString(resDrawingList));

        return resDrawing;
    }

}
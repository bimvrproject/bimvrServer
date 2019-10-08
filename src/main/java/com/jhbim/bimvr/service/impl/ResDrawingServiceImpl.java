package com.jhbim.bimvr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jhbim.bimvr.dao.entity.pojo.ResDrawing;
import com.jhbim.bimvr.dao.entity.vo.ResDrawingVO;
import com.jhbim.bimvr.dao.mapper.ResDrawingMapper;
import com.jhbim.bimvr.service.IResDrawingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResDrawingServiceImpl implements IResDrawingService {

    @Value("${server.project.baseUrl}")
    private String serverProjectBaseUrl;

    @Resource
    ResDrawingMapper resDrawingMapper;

    @Override
    public ResDrawing getOneRes(Long companyId, Long projectId, String modelId) {
        ResDrawing resDrawing = new ResDrawing();
        resDrawing.setCompanyId(companyId);
        resDrawing.setProjectId(projectId);
        resDrawing.setModelId(modelId);
        resDrawing = resDrawingMapper.getOneRes(resDrawing);
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
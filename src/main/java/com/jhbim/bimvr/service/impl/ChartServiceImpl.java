package com.jhbim.bimvr.service.impl;

import com.github.pagehelper.Page;
import com.jhbim.bimvr.dao.entity.pojo.Chart;
import com.jhbim.bimvr.dao.mapper.ChartMapper;
import com.jhbim.bimvr.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shuihu
 * @create 2019-11-25 15:58
 */
@Service
public class ChartServiceImpl implements IChartService {

    @Autowired
    private ChartMapper chartMapper;

    @Override
    public Page<Chart> findByPaging(String materialId) {

        Page<Chart> byPaging = chartMapper.findByPaging(materialId);
        return byPaging;
    }
}

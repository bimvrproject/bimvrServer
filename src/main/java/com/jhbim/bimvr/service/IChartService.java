package com.jhbim.bimvr.service;

import com.github.pagehelper.Page;
import com.jhbim.bimvr.dao.entity.pojo.Chart;

/**
 * @author shuihu
 * @create 2019-11-25 15:57
 */
public interface IChartService {
    //按类分页查询
    Page<Chart> findByPaging(String materialId);



}

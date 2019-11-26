package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Material;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-11-25 11:58
 */
public interface IMaterialService {

    //分类集合
    List<Material> select();

}

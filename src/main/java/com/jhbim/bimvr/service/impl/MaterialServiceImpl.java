package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.Material;
import com.jhbim.bimvr.dao.mapper.MaterialMapper;
import com.jhbim.bimvr.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-11-25 11:58
 */
@Service
public class MaterialServiceImpl implements IMaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> select() {
        List<Material> materials = materialMapper.select();
        return materials;
    }
}

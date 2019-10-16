package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Printscreen;

public interface PrintscreenMapper {

    int deleteByPrimaryKey(Long Printscreen_id);

    int insert(Printscreen printscreen);
}

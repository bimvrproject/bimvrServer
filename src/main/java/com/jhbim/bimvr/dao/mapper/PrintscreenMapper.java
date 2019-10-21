package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Printscreen;

import java.util.List;

public interface PrintscreenMapper {

    int deleteByPrimaryKey(Long Printscreen_id);

    int insert(Printscreen printscreen);

    List<Printscreen> selectproject(Printscreen printscreen);
}

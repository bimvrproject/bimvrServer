package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Printscreen;

import java.util.List;

public interface PrintscreenMapper {

    int deleteByPrimaryKey(Long Printscreen_id);

    int insert(Printscreen printscreen);

    List<Printscreen> selectproject(Printscreen printscreen);
    //点击截图的复选框获取到id 并存放在list集合里面
    List<Printscreen> dynamicForeachTest(Integer[] ids);
}

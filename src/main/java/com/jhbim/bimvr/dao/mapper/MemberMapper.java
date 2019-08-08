package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Member;

public interface MemberMapper {
    int insert(Member record);

    int insertSelective(Member record);
}
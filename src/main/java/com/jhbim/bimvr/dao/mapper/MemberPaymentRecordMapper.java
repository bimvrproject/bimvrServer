package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.MemberPaymentRecord;

public interface MemberPaymentRecordMapper {
    int deleteByPrimaryKey(Long paymentId);

    int insert(MemberPaymentRecord record);

    int insertSelective(MemberPaymentRecord record);

    MemberPaymentRecord selectByPrimaryKey(Long paymentId);

    int updateByPrimaryKeySelective(MemberPaymentRecord record);

    int updateByPrimaryKey(MemberPaymentRecord record);
}
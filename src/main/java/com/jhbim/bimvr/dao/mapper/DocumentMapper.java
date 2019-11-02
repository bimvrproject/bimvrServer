package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Document;

import java.util.List;

public interface DocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Document record);

    int insertSelective(Document record);

    Document selectByPrimaryKey(Long id);

    Document selectByProjectIdType(Long projectId,Integer type);

    List<Document> select();
    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);
}
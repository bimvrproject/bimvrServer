package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Document;

import java.util.List;

public interface DocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Document record);

    int insertSelective(Document record);

    Document selectByPrimaryKey(Long id);

    List<Document> select();
    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);
}
package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    List<Product> list();

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}
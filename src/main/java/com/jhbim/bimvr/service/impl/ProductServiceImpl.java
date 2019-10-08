package com.jhbim.bimvr.service.impl;

import java.util.List;

import com.jhbim.bimvr.dao.entity.pojo.Product;
import com.jhbim.bimvr.dao.mapper.ProductMapper;
import com.jhbim.bimvr.service.IProductService;
import com.jhbim.bimvr.pub.CheckMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Product> getProducts() {

		CheckMsg checkMsg = new CheckMsg();
		List<Product> list = productMapper.list();
		return list;

	}

	@Override
	public Product getProductById(String productId) {
		
		return productMapper.selectByPrimaryKey(productId);
	}

}

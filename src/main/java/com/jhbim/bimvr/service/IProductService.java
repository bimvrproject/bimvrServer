package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Product;

import java.util.List;



public interface IProductService {

	/**
	 * 获取所有产品列表
	 * @return
	 */
	public List<Product> getProducts();
	
	/**
	 * 根据产品ID查询产品详情
	 * @param productId
	 * @return
	 */
	public Product getProductById(String productId);
}

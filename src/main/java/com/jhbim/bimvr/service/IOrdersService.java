package com.jhbim.bimvr.service;


import com.jhbim.bimvr.dao.entity.pojo.Orders;

/**
 * 订单操作 service
 * @author wsh
 *
 */
public interface IOrdersService {

	/**
	 * 新增订单
	 * @param orders
	 */
	 Boolean saveOrder(Orders orders);
	
	/**
	 * 
	 * @Title: OrdersService.java
	 * @Package com.sihai.service
	 * @Description: 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
	 */
	Boolean updateOrderStatus(String id);
	
	/**
	 * 获取订单
	 * @param orderId
	 * @return
	 */
	 Orders getOrderById(String orderId);
	
}

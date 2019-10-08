package com.jhbim.bimvr.service.impl;


import com.jhbim.bimvr.dao.entity.pojo.Orders;
import com.jhbim.bimvr.dao.mapper.FlowMapper;
import com.jhbim.bimvr.dao.mapper.OrdersMapper;
import com.jhbim.bimvr.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class OrdersServiceImpl implements IOrdersService {

	@Autowired(required = false)
	private OrdersMapper ordersMapper;
	
	@Autowired
	private FlowMapper flowMapper;
	

	
	@Override
	public Boolean saveOrder(Orders orders) {
		boolean flag=false;
		try{
			ordersMapper.insert(orders);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Orders getOrderById(String orderId) {
		return ordersMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public Boolean updateOrderStatus(String id) {
		boolean flag=false;
		try{
			ordersMapper.updateByPrimaryKey(id);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

}

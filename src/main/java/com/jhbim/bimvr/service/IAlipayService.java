package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAlipayService {
    /**
      * 付款
      * @param alipayParam 付款参数
      * @return 付款返回值
      */
    String alipay(Orders orders);

    /**
      * 付款同步返回地址
      * @param request
      * @return
      */

    String synchronous(HttpServletRequest request);
    /**
      * 付款异步通知调用地址
      * @param request 新增参数
      * @return 新增返回值
      */
    void notify(HttpServletRequest request,HttpServletResponse response);

}

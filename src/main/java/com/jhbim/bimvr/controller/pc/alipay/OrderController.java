package com.jhbim.bimvr.controller.pc.alipay;

import com.jhbim.bimvr.dao.entity.pojo.Orders;
import com.jhbim.bimvr.dao.entity.pojo.Product;
import com.jhbim.bimvr.pub.Response;
import com.jhbim.bimvr.service.IOrdersService;
import com.jhbim.bimvr.service.IProductService;
import com.jhbim.bimvr.stauts.enums.OrderStatusEnum;
import com.jhbim.bimvr.stauts.unti.LeeJSONResult;
import com.jhbim.bimvr.stauts.unti.UUIDHexGenerator;
import com.jhbim.bimvr.utils.IdUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 生成点单
 * @author wsh
 * @version 1.0
 * @since 2019/8/14
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    final static org.slf4j.Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired(required = false)
    private IProductService productService;

    @Autowired
    private IOrdersService orderService;


/**
 * 生成固定三种订单
 *
 */

@RequestMapping("/create")
public Response create(@RequestBody String userId){
    Map<String,String> map=new HashMap<String, String>();
    Orders orders=new Orders();
    Product product=new Product();
    orders.setId(IdUtil.getIncreaseIdByCurrentTimeMillis());
    orders.setOrderNum(UUID.randomUUID().toString());
    orders.setSubject(product.getName());
    orders.setOrderAmount(product.getPrice());
    orders.setCreateTime(new Date());
    orders.setOrderStatus(OrderStatusEnum.WAIT_PAY.key);//状态
    orders.setProductId(userId);
    System.out.println(orders.toString());
    return Response.success().setData(orderService.saveOrder(orders));


}
    /**
     * 获取产品列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/products")
    public List<Product> products() throws Exception {

        return productService.getProducts();
    }

    /**
     * 进入确认页面
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goConfirm")
    public ModelAndView goConfirm(String productId) throws Exception {

        Product p = productService.getProductById(productId);

        ModelAndView mv = new ModelAndView("goConfirm");
        mv.addObject("p", p);

        return mv;
    }

    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createOrder2")
    public ModelAndView createOrde2r(Orders order) throws Exception {

        Product p = productService.getProductById(order.getProductId());

        String orderId = UUIDHexGenerator.generate();
        order.setId(orderId);
        order.setOrderNum(orderId);
        order.setCreateTime(new Date());
        order.setOrderAmount(String.valueOf(Float.valueOf(p.getPrice()) * order.getBuyCounts()));
        order.setOrderStatus(OrderStatusEnum.WAIT_PAY.key);
        orderService.saveOrder(order);

        ModelAndView mv = new ModelAndView("goPay");
        mv.addObject("order", order);
        mv.addObject("p", p);

        return mv;
    }

    /**
     * 分段提交
     * 	第一段：保存订单
     * @param order
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createOrder")
    @ResponseBody
    public LeeJSONResult createOrder(Orders order) throws Exception {

        Product p = productService.getProductById(order.getProductId());

        String orderId = UUIDHexGenerator.generate();
        order.setId(orderId);
        order.setOrderNum(orderId);
        order.setCreateTime(new Date());
        order.setOrderAmount(String.valueOf(Float.valueOf(p.getPrice()) * order.getBuyCounts()));
        order.setOrderStatus(OrderStatusEnum.WAIT_PAY.key);
        orderService.saveOrder(order);

        return LeeJSONResult.ok(orderId);
    }

    /**
     * 分段提交
     * 	第二段
     * @param orderId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goPay")
    public ModelAndView goPay(String orderId) throws Exception {

        Orders order = orderService.getOrderById(orderId);

        Product p = productService.getProductById(order.getProductId());

        ModelAndView mv = new ModelAndView("goPay");
        mv.addObject("order", order);
        mv.addObject("p", p);

        return mv;
    }
}

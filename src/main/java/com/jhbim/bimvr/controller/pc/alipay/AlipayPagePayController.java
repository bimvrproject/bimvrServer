package com.jhbim.bimvr.controller.pc.alipay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jhbim.bimvr.config.AlipayProperties;
import com.jhbim.bimvr.dao.entity.pojo.Orders;
import com.jhbim.bimvr.dao.entity.pojo.Product;
import com.jhbim.bimvr.pub.Response;
import com.jhbim.bimvr.service.IOrdersService;
import com.jhbim.bimvr.service.IProductService;
import com.jhbim.bimvr.stauts.enums.OrderStatusEnum;
import com.jhbim.bimvr.stauts.unti.UUIDHexGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;


/**
 * 支付宝-电脑网站支付.
 * @author wsh
 * @version 1.0
 * @since 2019/8/14
 */
@Controller
@RequestMapping("/pcpage")
public class AlipayPagePayController {
    private static final Logger logger = LoggerFactory.getLogger(AlipayPagePayController.class);
    @Autowired
    private AlipayProperties alipayProperties;
    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayController alipayController;

    @Autowired
    private IOrdersService ordersService;

    @Autowired(required = false)
    private IProductService productService;



    @PostMapping("/create")
    public Response create(@RequestBody String userId, Product product){
        Orders orders=new Orders();
        orders.setId(UUIDHexGenerator.generate());
        orders.setOrderNum(orders.getId());
        orders.setSubject(product.getName());
        orders.setOrderAmount(product.getPrice());
        orders.setCreateTime(new Date());
        orders.setOrderStatus(OrderStatusEnum.WAIT_PAY.key);//支付状态
        return Response.success().setData(ordersService.saveOrder(orders));

    }


    @RequestMapping("/sspay")
    public void PayPage(HttpServletResponse response , HttpSession httpSession) throws AlipayApiException, IOException {

        // 订单模型
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("vip3");
        model.setTotalAmount("1699");
        model.setBody("vip3");
        model.setProductCode(productCode);
        model.setTimeoutExpress("10m");//超过30分钟过期
        AlipayTradePagePayRequest pagePayRequest =new AlipayTradePagePayRequest();
        pagePayRequest.setReturnUrl(alipayProperties.getReturnUrl());
        pagePayRequest.setNotifyUrl(alipayProperties.getNotifyUrl());
        pagePayRequest.setBizModel(model);


        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String form = alipayClient.pageExecute(pagePayRequest).getBody();
        response.setContentType("text/html;charset=" + alipayProperties.getCharset());
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/returnUrl")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        response.setContentType("text/html;charset=" + alipayProperties.getCharset());

        boolean verifyResult = alipayController.rsaCheckV1(request);
        if(verifyResult){
            //验证成功
            //请在这里加上商户的业务逻辑程序代码，如保存支付宝交易号
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            return "pagePaySuccess";

        }else{
            return "pagePayFail";

        }
    }

}


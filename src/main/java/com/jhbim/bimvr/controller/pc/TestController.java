package com.jhbim.bimvr.controller.pc;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jhbim.bimvr.config.AlipayProperties;
import com.jhbim.bimvr.controller.pc.alipay.AlipayController;
import com.jhbim.bimvr.stauts.unti.UUIDHexGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/")
    public String hello(){
        return "Hello NetAPP 支付成功，准备发货";
    }



    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayController alipayController;


    @RequestMapping("/pay")
    public void gotoPayPage(HttpServletResponse response , HttpSession httpSession) throws AlipayApiException, IOException {

        // 订单模型
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("123");
        model.setTotalAmount("0.01");
        model.setBody("123");
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



}

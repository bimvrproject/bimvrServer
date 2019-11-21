package com.jhbim.bimvr.controller.app;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jhbim.bimvr.config.AlipayProperties;
import com.jhbim.bimvr.dao.entity.pojo.Version;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.service.IVersionService;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private AlipayClient alipayClient;

    @Resource
    IVersionService versionService;
    /*
     * 检查版本信息
     * 拿着版本号查询数据库
     * */
    @RequestMapping("/checkVersion")
    public Result checkVersion(){
        Version reVersion = versionService.checkVersion();
        return new Result(ResultStatusCode.OK,reVersion);
    }

    @RequestMapping("/pay1")
    public void gotoPayPage1(HttpServletResponse response) throws AlipayApiException, IOException {
        // 订单模型
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("超级会员");
        model.setTotalAmount("1699");
        model.setBody("菁合科技超级会员充值");
        model.setProductCode(productCode);
        model.setTimeoutExpress("30m");//超过30分钟过期
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
    @RequestMapping("/pay2")
    public void gotoPayPage2(HttpServletResponse response) throws AlipayApiException, IOException {
        // 订单模型
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("专享会员");
        model.setTotalAmount("799");
        model.setBody("菁合科技专享会员充值");
        model.setProductCode(productCode);
        model.setTimeoutExpress("30m");//超过30分钟过期
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
    @RequestMapping("/pay3")
    public void gotoPayPage3(HttpServletResponse response) throws AlipayApiException, IOException {
        // 订单模型
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("特权会员");
        model.setTotalAmount("1399");
        model.setBody("菁合科技特权会员充值");
        model.setProductCode(productCode);
        model.setTimeoutExpress("30m");//超过30分钟过期
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

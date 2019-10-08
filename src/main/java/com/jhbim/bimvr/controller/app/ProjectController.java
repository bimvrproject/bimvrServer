package com.jhbim.bimvr.controller.app;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jhbim.bimvr.config.AlipayProperties;
import com.jhbim.bimvr.controller.pc.alipay.AlipayController;
import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.HomeResult;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.service.IProjectService;
import com.jhbim.bimvr.service.IResDrawingService;
import com.jhbim.bimvr.service.IResMeterialService;
import com.jhbim.bimvr.service.IResModelService;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/${version}/userProject")
public class ProjectController {

    @Resource
    IProjectService projectService;
    @Resource
    IResModelService resModelService;
    @Resource
    IResMeterialService resMeterialService;
    @Resource
    IResDrawingService resDrawingService;


    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayController alipayController;
    /**
     *     通过用户名获取  所属用户下的项目
     *     用户名通过shiro获取
     * */
    @RequestMapping("/getProject")
    public Result getProject(){
        User user = ShiroUtil.getUser();
        HomeResult homeResult = new HomeResult();
        List<Project> projectList = projectService.getProject(user);
        homeResult.setProjectList(projectList);
        homeResult.setUsername(user.getUserName());
        homeResult.setCompanyId(user.getCompanyId());
        return new Result(ResultStatusCode.OK,homeResult);
    }

    @RequestMapping("/getResModel")
    public Result getResModel(Long companyId, Long projectId, String modelId) {
        ResModel resModel = resModelService.getOneRes(companyId, projectId, modelId);
        return new Result(ResultStatusCode.OK, resModel);
    }

    @RequestMapping("/getResMeterial")
    public Result getResMeterial(Long companyId, Long projectId, String modelId) {
        ResMeterial resMeterial = resMeterialService.getOneRes(companyId, projectId, modelId);
        return new Result(ResultStatusCode.OK, resMeterial);
    }

    @RequestMapping("/getResDrawing")
    public Result getResDrawing(Long companyId, Long projectId, String modelId) {
        ResDrawing resDrawing = resDrawingService.getOneRes(companyId, projectId, modelId);
        return new Result(ResultStatusCode.OK, resDrawing);
    }

    @RequestMapping("/pay")
    public void gotoPayPage(HttpServletResponse response) throws AlipayApiException, IOException {
        // 订单模型
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("123");
        model.setTotalAmount("0.01");
        model.setBody("123");
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

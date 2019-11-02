package com.jhbim.bimvr.controller.app.project;


import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.app.AppHomeResult;
import com.jhbim.bimvr.pub.Response;
import com.jhbim.bimvr.service.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuihu
 * @create 2019-10-30 14:41
 */
@RequestMapping("/${version}/appProject")
@RestController
public class AppProjectController {


    @Autowired
    private IAppProjectService iAppProjectService;

    @Resource
    IAppResModelService appResModelService;
    @Resource
    IAppResMeterialService appResMeterialService;
    @Resource
    IAppResDrawingService appResDrawingService;

    /**
     * 查出用户所属公司的所有模型
     * @return
     */
    @RequestMapping("/getProject")
   public Result getProject(){
        User user = ShiroUtil.getUser();
        List<AppProject> select = iAppProjectService.select();
        AppHomeResult homeResult = new AppHomeResult();
        homeResult.setAppProjectList(select);
        homeResult.setUsername(user.getUserName());
        homeResult.setCompanyId(user.getCompanyId());
        return new Result(ResultStatusCode.OK,homeResult);
   }

    @RequestMapping("/getResModel")
    public Result getResModel(Long companyId, Long projectId, String modelId) {
        AppResModel resModel = appResModelService.getOneRes(companyId, projectId, modelId);
        return new Result(ResultStatusCode.OK, resModel);
    }

    @RequestMapping("/getResMeterial")
    public Result getResMeterial(Long companyId, Long projectId, String modelId) {
        AppResMeterial resMeterial = appResMeterialService.getOneRes(companyId, projectId, modelId);
        return new Result(ResultStatusCode.OK, resMeterial);
    }

    @RequestMapping("/getResDrawing")
    public Result getResDrawing(Long companyId, Long projectId, String modelId) {
        AppResDrawing resDrawing = appResDrawingService.getOneRes(companyId, projectId, modelId);
        return new Result(ResultStatusCode.OK, resDrawing);
    }

}

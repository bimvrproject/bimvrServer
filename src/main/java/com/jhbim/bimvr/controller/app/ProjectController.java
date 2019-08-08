package com.jhbim.bimvr.controller.app;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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


}

package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.ResModel;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.ResModelMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/${version}/model")
public class ModelController {

    @Resource
    ResModelMapper resModelMapper;

    /**
     * 增加模型
     * @param model
     * @return
     */
    @PostMapping("/addmodel")
    public Result addmodel(@RequestBody ResModel model){
        resModelMapper.insertSelective(model);
        return new Result(ResultStatusCode.OK,"模型增加成功");
    }

    /**
     * 删除模型
     * @param id
     * @return
     */
    @DeleteMapping("/deletemodel/{id}")
    public Result deletemodel(@PathVariable Integer id){
        resModelMapper.deleteByPrimaryKey(id);
        return new Result(ResultStatusCode.OK,"模型删除成功");
    }

    /**
     * 根据项目id展示模型
     * @param projectid
     * @return
     */
    @GetMapping("/modelprojectid/{modelid}/{projectid}")
    public Result selectprojectid(@PathVariable(value = "modelid") String modelid,@PathVariable(value = "projectid") Long projectid,HttpServletRequest request){
        User user = ShiroUtil.getUser();
            //存储指定项目的id
            ServletContext application=request.getSession().getServletContext();
            application.setAttribute("ModelProject_id",projectid);
             System.out.println("application"+application.getAttribute("ModelProject_id"));
             ResModel resModel=new ResModel();
             resModel.setModelId(modelid);
             resModel.setProjectId(projectid);
             resModel.setCompanyId(user.getCompanyId());
            ResModel  resM=resModelMapper.getOneRes(resModel);
        return new Result(ResultStatusCode.OK,resM);
    }

}

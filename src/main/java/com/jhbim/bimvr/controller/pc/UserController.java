package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Company;
import com.jhbim.bimvr.dao.entity.pojo.CompanyProject;
import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.CompanyMapper;
import com.jhbim.bimvr.dao.mapper.CompanyProjectMapper;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/${version}/pcUser")
public class UserController {

    @Resource
    CompanyMapper companyMapper;
    @Resource
    CompanyProjectMapper companyProjectMapper;
    @Resource
    ProjectMapper projectMapper;

    /**
     *   根据token去获取用户信息
     * */
    @RequestMapping("/getUserByToken")
    public Result getUser(){
        User user = ShiroUtil.getUser();
        user.getCompanyId();//测试
        return  new Result(ResultStatusCode.OK,user);
    }

    /**
     * 根据当前登录用户查询出有关用户的所有的项目...
     * @param Userid  用户的id
     * @return
     */
    @GetMapping("/getUserProject/{Userid}")
    public Result getUserProject(@PathVariable Long Userid, ModelMap modelMap){
        User user=ShiroUtil.getUser();
        Company company = companyMapper.selectByPrimaryKey(Userid);
        CompanyProject companyProject = companyProjectMapper.selectByPrimaryKey(company.getCompanyId());
        List<Project> projectList=projectMapper.findById(companyProject.getProjectId());
        modelMap.put("project",projectList);
        return new Result(ResultStatusCode.OK,user);
    }
}

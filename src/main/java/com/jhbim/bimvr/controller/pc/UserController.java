package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.UserVo;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    /**
     *   根据token去获取用户信息
     * */
    @RequestMapping("/getUserByToken")
    public Result getUser(){
        User user = ShiroUtil.getUser();
//        user.getCompanyId();//测试
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

    /**
     * 查询用户表和角色表的字段  并存储到userVo实体类中
     * @param phone
     * @return
     */
    @GetMapping("/getphoneandrole/{phone}")
    public Result getphoneandrole(@PathVariable String phone){
        User user=userMapper.getByPhone(phone);
        Role role=roleMapper.selectByPrimaryKey(user.getRoleId());
        UserVo userVo=new UserVo();
        userVo.setPhone(user.getPhone());
        userVo.setPicture(user.getPicture());
        userVo.setRoleName(role.getDescription());
        return new Result(ResultStatusCode.UserVo,userVo);
    }

    /**
     * 修改用户昵称
     * @param userName  昵称
     * @param phone 手机号
     * @return
     */
    @PostMapping("/updatausername")
    public Result updatausername(String userName,String phone){
        userMapper.updatausername(userName,phone);
        return new Result(ResultStatusCode.OK,"昵称修改成功");
    }

    /**
     * 修改所属公司
     * @param companyname 所属公司
     * @param phone 手机号
     * @return
     */
    @PostMapping("/updatacompanyname")
    public Result updatacompanyname(String companyname,String phone){
        userMapper.updatacompanyname(companyname,phone);
        return new Result(ResultStatusCode.OK,"所属公司修改成功");
    }

    /**
     * 修改职位
     * @param position 职位
     * @param phone  手机号
     * @return
     */
    @PostMapping("/updataposition")
    public Result updataposition( String position,String phone){
        userMapper.updataposition(position,phone);
        return new Result(ResultStatusCode.OK,"职位修改成功");
    }

    /**
     * 修改备注
     * @param remarks 备注
     * @param phone 手机号
     * @return
     */
    @PostMapping("/updataremarks")
    public Result updataremarks(String remarks,String phone){
        userMapper.updataremarks(remarks,phone);
        return new Result(ResultStatusCode.OK,"备注修改成功");
    }
}

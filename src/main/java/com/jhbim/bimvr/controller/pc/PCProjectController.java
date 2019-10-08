package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.CompanyProject;
import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.CompanyProjectMapper;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.dao.mapper.ResModelMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.Ftp;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/${version}/pcproject")
public class PCProjectController {

    @Resource
    ProjectMapper projectMapper;
    @Resource
    CompanyProjectMapper companyProjectMapper;
    @Resource
    ResModelMapper resModelMapper;
    @Resource //随机数
    IdWorker idWorker;
    /**
     * 增加项目
     * @param project
     * @return
     */
    @PostMapping("/addproject")
    public Result addproject(@RequestBody Project project ,HttpServletRequest request){
        //获取用户的信息
        User user= ShiroUtil.getUser();
        String projectid=user.getCompanyId()+idWorker.nextId()+"";
        Long proid=Long.valueOf(projectid);
        //增加项目
        project.setProjectId(proid);
        project.setProjectModelAddr("http://39.96.59.142:8080/project/res_picture/0.png");
        project.setProjectStatus(3);
        projectMapper.insertSelective(project);
        //存值在application作用域
        ServletContext application =request.getSession().getServletContext();
        //保存项目的id
        application.setAttribute("Project_ID",project.getProjectId());
        //保存当前登录人员所在的公司id
        application.setAttribute("User_CompanyID",user.getCompanyId());
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"+application.getAttribute("ModelProject_id"));
        application.removeAttribute("ModelProject_id");
        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+application.getAttribute("ModelProject_id"));
        //增加公司和项目的记录
        CompanyProject companyProject=new CompanyProject();
        companyProject.setCompanyId(user.getCompanyId());
        companyProject.setProjectId(proid);
        companyProject.setCreateTime(project.getStartTime());
        companyProject.setUpdateTime(project.getStartTime());
        companyProjectMapper.insert(companyProject);
        return new Result(ResultStatusCode.OK,"项目增加成功");
    }
    /**
     * 删除项目
     * @param delid
     * @return
     */
    @DeleteMapping("/deleteproject/{delid}")
    public Result deleteproject(@PathVariable Long delid){
        projectMapper.deleteByPrimaryKey(delid);        //删除项目
        companyProjectMapper.deleteprojectid(delid);       //删除项目和公司的关系表
        resModelMapper.deleteproject(delid);        //根据项目id删除模型表的数据
//        try {
//            Ftp ftpFileUpload = Ftp.getSftpUtil("36.112.65.110",22,"root","jhkj991102");
//            System.out.println("删除文件夹的id----"+delid);
//            String IP="/var/lib/tomcat9/webapps/ROOT/"+delid;
//            ftpFileUpload.deleteproject(IP);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new Result(ResultStatusCode.OK,"项目删除成功");
    }

}

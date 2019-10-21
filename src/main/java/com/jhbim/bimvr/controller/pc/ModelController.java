package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Printscreen;
import com.jhbim.bimvr.dao.entity.pojo.ResModel;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.PrintscreenVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.PrintscreenMapper;
import com.jhbim.bimvr.dao.mapper.ResModelMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.Ftp;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/${version}/model")
public class ModelController {

    @Resource
    ResModelMapper resModelMapper;
    @Resource
    PrintscreenMapper printscreenMapper;

    /**
     * 截图
     * @param request
     * @return
     */
    @GetMapping("/OpenCmd")
    public Result OpenCmd(HttpServletRequest request){
        User user= ShiroUtil.getUser();
        ServletContext application =request.getSession().getServletContext();
        //项目id
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");

        try {
            Long project_id=0L;
            if(ModelProjectid==null){
                project_id=project_ids;
                System.out.println("等于null----"+project_id);
            }
            if(ModelProjectid!=null){
                project_id=ModelProjectid;
                System.out.println("不等于null-----"+project_id);
            }
            String address="E:/tomcat9/apache-tomcat-9.0.26-windows-x64/apache-tomcat-9.0.26/webapps/ROOT/Printscreen/";
            File file=new File(address);
            if(!file.exists()) {
                file.mkdirs();
            }
            String images= UUID.randomUUID().toString()+".jpg";
            String tupian="Printscreen/"+project_id+"/"+ images;
            Runtime.getRuntime().exec("C:\\WINDOWS\\system32\\cmd.exe /c C:\\Users\\Administrator\\Desktop\\getScreen.exe E:/tomcat9/apache-tomcat-9.0.26-windows-x64/apache-tomcat-9.0.26/webapps/ROOT/"+tupian);

            //保存到截图表里
            Printscreen printscreen=new Printscreen();
            printscreen.setPrintscreenUser(user.getPhone());
            printscreen.setImages(tupian);
            printscreen.setProjectId(String.valueOf(project_id));
            printscreen.setModelId(1L);
            printscreenMapper.insert(printscreen);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultStatusCode.OK,"截图成功");
    }

    /**
     * 查询截图表展示到页面
     * @param projectId
     * @return
     */
    @GetMapping("/selectPrintscreen/{projectId}")
    public Result selectPrintscreen(@PathVariable String projectId){
        User user= ShiroUtil.getUser();
        Printscreen printscreen=new Printscreen();
        printscreen.setPrintscreenUser(user.getPhone());
        printscreen.setProjectId(projectId);
        printscreen.setModelId(1L);
        List<Printscreen> printscreenList=printscreenMapper.selectproject(printscreen);
        PrintscreenVo printscreenVo=new PrintscreenVo();
        printscreenVo.setPrintscreenslist(printscreenList);
        return new Result(ResultStatusCode.OK,printscreenVo );
    }
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
    public Result selectprojectid(@PathVariable(value = "modelid") String modelid, @PathVariable(value = "projectid") Long projectid, HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
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

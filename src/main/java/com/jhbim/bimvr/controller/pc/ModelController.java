package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Printscreen;
import com.jhbim.bimvr.dao.entity.pojo.ResModel;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.PrintscreenVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.PrintscreenMapper;
import com.jhbim.bimvr.dao.mapper.ResModelMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import com.jhbim.bimvr.utils.Zip;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
     * 保存截图
     * @param printscreen
     * @return
     */
    @PostMapping("/addprintscreen")
    @ResponseBody
    public Result Addprintscreen(@RequestBody Printscreen printscreen ,HttpServletRequest request){
        ServletContext application=request.getSession().getServletContext();
        String userphone= (String) application.getAttribute("User_Phone");
        printscreen.setPrintscreenUser(userphone);
        printscreenMapper.insert(printscreen);
        return new Result(ResultStatusCode.OK,"ok");
    }

    /**
     * 查询截图表展示到页面
     * @param projectId
     * @return
     */
    @GetMapping("/selectPrintscreen/{projectId}/{modelid}/{typdprint}")
    public Result selectPrintscreen(@PathVariable String projectId,@PathVariable Long modelid,@PathVariable Long  typdprint){
        User user= ShiroUtil.getUser();
        Printscreen printscreen=new Printscreen();
        printscreen.setPrintscreenUser(user.getPhone());
        printscreen.setProjectId(projectId);
        printscreen.setModelId(modelid);
        printscreen.setTypdprint(typdprint);
        List<Printscreen> printscreenList=printscreenMapper.selectproject(printscreen);
        PrintscreenVo printscreenVo=new PrintscreenVo();
        printscreenVo.setPrintscreenslist(printscreenList);
        return new Result(ResultStatusCode.OK,printscreenVo );
    }

    @GetMapping("/dynamicForeachTest")
    public Result dynamicForeachTest(Integer[] ids){
        String ip="D:\\Tomcat9\\apache-tomcat-9.0.27\\webapps\\ROOT\\";
        List<Printscreen> printscreenList=printscreenMapper.dynamicForeachTest(ids);
        String folder= UUID.randomUUID().toString();
        String path="D:\\Tomcat9\\apache-tomcat-9.0.27\\webapps\\ROOT\\Zip\\"+folder;
        File f=new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        for (Printscreen p : printscreenList) {
            Zip.GenerateImage(p.getImages(),f.getPath()+"\\"+ UUID.randomUUID().toString() +".jpg");
        }
        OutputStream is = null;//创建Test.zip文件
        String compression=f.getPath()+".zip";
        try {
            is = new FileOutputStream(compression);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Boolean KeepDirStructure = true;
        Zip.toZip(f.toString(), is, KeepDirStructure);
        /**
         * 将文件夹及其中所有文件删除
         */
        Zip.delFolder(f.toString());
        String zip=compression.substring(compression.lastIndexOf("\\")+1);
        String address="http://192.168.6.152:8080/Zip/"+zip;
//        String address="http://36.112.65.110:8080/Zip/"+zip;
        return new Result(ResultStatusCode.OK,address);
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

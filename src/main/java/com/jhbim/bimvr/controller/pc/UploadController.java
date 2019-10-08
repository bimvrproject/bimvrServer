package com.jhbim.bimvr.controller.pc;
import com.jhbim.bimvr.dao.entity.pojo.ResDrawing;
import com.jhbim.bimvr.dao.entity.pojo.ResModel;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.mapper.ResDrawingMapper;
import com.jhbim.bimvr.dao.mapper.ResModelMapper;
import com.jhbim.bimvr.utils.FileUploadUtils;
import com.jhbim.bimvr.utils.Ftp;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


@RestController
@RequestMapping("/${version}/Upload")
public class UploadController {
    @Resource
    FileUploadUtils fileUploadUtils;
    @Resource
    ResModelMapper resModelMapper;
    @Resource
    ResDrawingMapper drawingMapper;

    /**
     * 上传建筑模型到服务器
     * @param file
     */
    @PostMapping("/uploadCategory")
    public  void uploadFolder(MultipartFile[] file,HttpServletRequest request){
        Long project_id=0L;
        //先保存在本地
        String address="C:\\Building";
        //application作用域
        ServletContext application =request.getSession().getServletContext();
        //项目id
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");
        try {
            //保存到本地
            fileUploadUtils.saveMultiFile(address, file);
            //服务器的信息
            Ftp ftpFileUpload = Ftp.getSftpUtil("36.112.65.110",22,"root","jhkj991102");
            //保存服务器的路径
            String IP="/var/lib/tomcat9/webapps/ROOT/";
            System.out.println(ModelProjectid+"****************************////////////////////////////////////////////////////*/*/*/*/*");
            if(ModelProjectid==null){
                    project_id=project_ids;
                System.out.println("等于null----"+project_id);
            }
            if(ModelProjectid!=null){
                project_id=ModelProjectid;
                System.out.println("不等于null-----"+project_id);
            }
            System.out.println("建筑模型上传服务器的id*************************"+project_id);
            //创建日期目录
            ftpFileUpload.upload(IP+project_id,"");
            //当前日期+1表示建筑模型
            String one=project_id+"/1";
            String model1=project_id+"/1"+"/model";     //模型
            String price1=project_id+"/1"+"/price";     //造价
            //创建日期目录下的1,
            ftpFileUpload.upload(IP+one,"");
            ftpFileUpload.upload(IP+model1,"");
            ftpFileUpload.upload(IP+price1,"");
            //增加建筑模型
            ftpFileUpload.upload(IP+model1,address);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 建筑模型保存到数据库
     * @param request
     */
    @PostMapping("/addBuildModel")
    public void addBuildModel(HttpServletRequest request){
        User user = ShiroUtil.getUser();
        //application作用域
        ServletContext application =request.getSession().getServletContext();
        application.setAttribute("UserCompanyID",user.getCompanyId());
        //项目id
        Long project_id=0L;
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");
        if(ModelProjectid==null){
            project_id=project_ids;
            System.out.println("等于null----"+project_id);
        }
        if(ModelProjectid!=null){
            project_id=ModelProjectid;
            System.out.println("不等于null-----"+project_id);
        }
        System.out.println("建筑模型的id----"+project_id);
        //登录人当前的所属的公司id
        Long User_CompanyID= (Long) application.getAttribute("User_CompanyID");
        //  存储到模型表里
        ResModel resModel=new ResModel();
        resModel.setModelId("1");       //模型
        resModel.setProjectId(project_id);  //项目id
        resModel.setCompanyId(user.getCompanyId()); //公司id
        resModel.setUrl("/"+project_id+"/1/model"+"/model.html");       //url地址
        resModelMapper.insertSelective(resModel);
        application.removeAttribute("ModelProject_id");         //删除modelproject里面的项目id
        System.out.println(ModelProjectid+"删除applicationModelProject");
    }


    /**
     * 上传建筑图纸平面
     * @param file
     * @param request
     */
    @PostMapping("/uploadpicture")
    public void uploadpicture(MultipartFile[] file,HttpServletRequest request){
        //application作用域
        ServletContext application =request.getSession().getServletContext();
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");
        Long usrcompanyid= (Long) application.getAttribute("User_CompanyId");
        Long project_id=0L;
        if(ModelProjectid==null){
            project_id=project_ids;
        }
        if(ModelProjectid!=null){
            project_id=ModelProjectid;
        }
        //先保存在本地
        String address="C:\\Picture";
        try {
            for(int i=0;i< file.length;i++){
                ResDrawing drawing=new ResDrawing();
                drawing.setModelId("1");        //建筑模型
                drawing.setProjectId(project_id); //项目id
                drawing.setCompanyId(usrcompanyid);      //公司id
                drawing.setUrl("/"+project_id+"/1"+"/drawing/"+file[i].getOriginalFilename());  //图纸地址
                drawing.setDrawName("平面图纸");    //名称
                drawing.setDrawType(1);     //平面图纸
                drawingMapper.insertSelective(drawing);
            }
            //保存到本地
            fileUploadUtils.saveMultiFile(address, file);
            //项目id

            //服务器的信息
            Ftp ftp = Ftp.getSftpUtil("36.112.65.110",22,"root","jhkj991102");
            //保存服务器的路径
            String IP="/var/lib/tomcat9/webapps/ROOT/";

            //创建日期目录
            ftp.upload(IP+project_id,"");
            //当前日期+1表示建筑模型
            String one=project_id+"/1";
            String drawing1=project_id+"/1"+"/drawing";     //图片
            //创建日期目录下的1,
            ftp.upload(IP+one,"");
            ftp.upload(IP+drawing1,"");
            //增加建筑模型
            ftp.upload(IP+drawing1,address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传建筑图纸立面
     * @param file
     * @param request
     */
    @PostMapping("/uploadpicturelimian")
    public void uploadpicturelimian(MultipartFile[] file,HttpServletRequest request){
        //application作用域
        ServletContext application =request.getSession().getServletContext();
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");
        Long usrcompanyid= (Long) application.getAttribute("User_CompanyId");
        Long project_id=0L;
        if(ModelProjectid==null){
            project_id=project_ids;
        }
        if(ModelProjectid!=null){
            project_id=ModelProjectid;
        }
        //先保存在本地
        String address="C:\\Picture";
        try {
            for(int i=0;i< file.length;i++){
                ResDrawing drawing=new ResDrawing();
                drawing.setModelId("1");        //建筑模型
                drawing.setProjectId(project_id); //项目id
                drawing.setCompanyId(usrcompanyid);      //公司id
                drawing.setUrl("/"+project_id+"/1"+"/drawing/"+file[i].getOriginalFilename());  //图纸地址
                drawing.setDrawName("立面图纸");    //名称
                drawing.setDrawType(2);     //平面图纸
                drawingMapper.insertSelective(drawing);
            }
            //保存到本地
            fileUploadUtils.saveMultiFile(address, file);
            //项目id

            //服务器的信息
            Ftp ftp = Ftp.getSftpUtil("36.112.65.110",22,"root","jhkj991102");
            //保存服务器的路径
            String IP="/var/lib/tomcat9/webapps/ROOT/";

            //创建日期目录
            ftp.upload(IP+project_id,"");
            //当前日期+1表示建筑模型
            String one=project_id+"/1";
            String drawing1=project_id+"/1"+"/drawing";     //图片
            //创建日期目录下的1,
            ftp.upload(IP+one,"");
            ftp.upload(IP+drawing1,"");
            //增加建筑模型
            ftp.upload(IP+drawing1,address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 管道模型上传到服务器
     * @param file
     * @param
     */
    @PostMapping("/uploadpipe")
    public void uploadpipe(MultipartFile[] file,HttpServletRequest request){
        Long project_id=0L;
        //先保存在本地
        String address="C:\\Building";
        //application作用域
        ServletContext application =request.getSession().getServletContext();
        //项目id
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");
        try {
            //保存到本地
            fileUploadUtils.saveMultiFile(address, file);
            //服务器的信息
            Ftp ftpFileUpload = Ftp.getSftpUtil("36.112.65.110",22,"root","jhkj991102");
            //保存服务器的路径
            String IP="/var/lib/tomcat9/webapps/ROOT/";
            if(ModelProjectid==null){
                project_id=project_ids;
                System.out.println("等于null----"+project_id);
            }
            if(ModelProjectid!=null){
                project_id=ModelProjectid;
                System.out.println("不等于null-----"+project_id);
            }
            application.setAttribute("aaaa",project_id);
            //创建日期目录
            ftpFileUpload.upload(IP+project_id,"");
            //当前日期+1表示建筑模型
            String one=project_id+"/2";
            String model1=project_id+"/2"+"/model";     //模型
            String price1=project_id+"/2"+"/price";     //造价
            //创建日期目录下的1,
            ftpFileUpload.upload(IP+one,"");
            ftpFileUpload.upload(IP+model1,"");
            ftpFileUpload.upload(IP+price1,"");
            //增加建筑模型
            ftpFileUpload.upload(IP+model1,address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 管线模型保存到数据库
     * @param request
     */
    @PostMapping("/addPipeModel")
    public void addPipeModel(HttpServletRequest request){
        User user = ShiroUtil.getUser();
        //application作用域
        ServletContext application=request.getSession().getServletContext();
        Long modelid= (Long) application.getAttribute("aaaa");       //项目id
        System.out.println("管道模型的id----"+modelid);
        //  存储到模型表里
        ResModel resModel=new ResModel();
        resModel.setModelId("2");       //模型
        resModel.setProjectId(modelid);  //项目id
        resModel.setCompanyId(user.getCompanyId()); //公司id
        resModel.setUrl("/"+modelid+"/2/model/model.html");       //url地址
        resModelMapper.insertSelective(resModel);
        application.removeAttribute("ModelProject_id"); //删除modelproject里面的项目id
        System.out.println(modelid+"删除applicationModelProject");

    }

    /**
     * 上传管道图纸平面
     * @param file
     * @param request
     */
    @PostMapping("/uploadpipepingmian")
    public void uploadpipepingmian(MultipartFile[] file,HttpServletRequest request){
        //application作用域
        ServletContext application =request.getSession().getServletContext();
        //项目id
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");
        Long usrcompanyid= (Long) application.getAttribute("User_CompanyId");
        Long project_id=0L;
        if(ModelProjectid==null){
            project_id=project_ids;
            System.out.println("等于null----"+project_id);
        }
        if(ModelProjectid!=null){
            project_id=ModelProjectid;
            System.out.println("不等于null-----"+project_id);
        }
        //先保存在本地
        String address="C:\\Picture";

        try {

            for(int i=0;i< file.length;i++){
                ResDrawing drawing=new ResDrawing();
                drawing.setModelId("2");        //建筑模型
                drawing.setProjectId(project_id); //项目id
                drawing.setCompanyId(usrcompanyid);      //公司id
                drawing.setUrl("/"+project_id+"/2"+"/drawing/"+file[i].getOriginalFilename());  //图纸地址
                drawing.setDrawName("平面图纸");    //名称
                drawing.setDrawType(1);     //平面图纸
                drawingMapper.insertSelective(drawing);
            }
            //保存到本地
            fileUploadUtils.saveMultiFile(address, file);
            //服务器的信息
            Ftp ftpFileUpload = Ftp.getSftpUtil("36.112.65.110",22,"root","jhkj991102");
            //保存服务器的路径
            String IP="/var/lib/tomcat9/webapps/ROOT/";

            //创建日期目录
            ftpFileUpload.upload(IP+project_id,"");
            //当前日期+1表示建筑模型
            String one=project_id+"/2";
            String drawing1=project_id+"/2"+"/drawing";     //图片
            //创建日期目录下的1,
            ftpFileUpload.upload(IP+one,"");
            ftpFileUpload.upload(IP+drawing1,"");
            //增加建筑模型
            ftpFileUpload.upload(IP+drawing1,address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传管道图纸立面
     * @param file
     * @param request
     */
    @PostMapping("/uploadpipelimian")
    public void uploadpipelimian(MultipartFile[] file,HttpServletRequest request){
        //application作用域
        ServletContext application =request.getSession().getServletContext();
        //项目id
        Long project_ids= (Long) application.getAttribute("Project_ID");
        Long ModelProjectid= (Long) application.getAttribute("ModelProject_id");
        Long usrcompanyid= (Long) application.getAttribute("User_CompanyId");
        Long project_id=0L;
        if(ModelProjectid==null){
            project_id=project_ids;
            System.out.println("等于null----"+project_id);
        }
        if(ModelProjectid!=null){
            project_id=ModelProjectid;
            System.out.println("不等于null-----"+project_id);
        }
        //先保存在本地
        String address="C:\\Picture";

        try {

            for(int i=0;i< file.length;i++){
                ResDrawing drawing=new ResDrawing();
                drawing.setModelId("2");        //建筑模型
                drawing.setProjectId(project_id); //项目id
                drawing.setCompanyId(usrcompanyid);      //公司id
                drawing.setUrl("/"+project_id+"/2"+"/drawing/"+file[i].getOriginalFilename());  //图纸地址
                drawing.setDrawName("平面图纸");    //名称
                drawing.setDrawType(2);     //平面图纸
                drawingMapper.insertSelective(drawing);
            }
            //保存到本地
            fileUploadUtils.saveMultiFile(address, file);
            //服务器的信息
            Ftp ftpFileUpload = Ftp.getSftpUtil("36.112.65.110",22,"root","jhkj991102");
            //保存服务器的路径
            String IP="/var/lib/tomcat9/webapps/ROOT/";

            //创建日期目录
            ftpFileUpload.upload(IP+project_id,"");
            //当前日期+1表示建筑模型
            String one=project_id+"/2";
            String drawing1=project_id+"/2"+"/drawing";     //图片
            //创建日期目录下的1,
            ftpFileUpload.upload(IP+one,"");
            ftpFileUpload.upload(IP+drawing1,"");
            //增加建筑模型
            ftpFileUpload.upload(IP+drawing1,address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

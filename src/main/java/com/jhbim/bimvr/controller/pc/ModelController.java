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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public Result Addprintscreen(@RequestBody Printscreen printscreen){
        User user=ShiroUtil.getUser();
        printscreen.setPrintscreenUser(user.getPhone());
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
    public Result dynamicForeachTest(Integer[] ids) {
        List<Printscreen> list = printscreenMapper.dynamicForeachTest(ids);
        File file = new File("D:\\QRcode");
        Zip.creatFile(file);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssss");
        String longTime = sdf.format(new Date());
        Integer index = 0;

        for (Printscreen printscreen : list) {
            index++;
            String newName = longTime + index.toString() + ".jpg";
            String filePath = file + "/" + newName;

            if (printscreen.getImages() != null) {
                Boolean result = Zip.Base64ToPicture(printscreen.getImages().substring(22), filePath);
                if (result == false)
                    return new Result(ResultStatusCode.FAIL,"压缩失败");
            }
            /**
             * 将二维码文件夹压缩
             */
            OutputStream is = null;//创建Test.zip文件
            try {
                is = new FileOutputStream("D:\\Tomcat9\\apache-tomcat-9.0.27\\webapps\\ROOT\\Printscreen\\aa.zip");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Boolean KeepDirStructure = true;
            Zip.toZip(file.toString(), is, KeepDirStructure);
            /**
             * 将二维码图片文件夹及其中所有文件删除
             */
            Zip.delFolder(file.toString());


        }
        return new Result(ResultStatusCode.OK,"压缩成功");
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

package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.ResMeterial;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.ResMeterialMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ExcelToHtml;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${version}/excel")

public class ExcelToHtmlController {
    @Resource
    ResMeterialMapper resMeterialMapper;

    /**
     * 查询清单的模型id项目id和登录人展示清单的位置
     * @param projectid
     * @param modelid
     * @return
     */
    @GetMapping("/addressurl/{projectid}/{modelid}")
    public Result addressurl(@PathVariable  Long projectid, @PathVariable String modelid){
        User user= ShiroUtil.getUser();
        System.out.println(user.getCompanyId());
        ResMeterial resMeterial=new ResMeterial();
        resMeterial.setCompanyId(user.getCompanyId());
        resMeterial.setProjectId(projectid);
        resMeterial.setModelId(modelid);
        return new Result(ResultStatusCode.OK,resMeterialMapper.getOneRes(resMeterial));
    }

    /**
     * 将excel转变成html
     * @param addressurl
     * @return
     */
    @GetMapping("/exceltohtml")
    public Result exceltohtml(String addressurl){
        ExcelToHtml excelToHtml=new ExcelToHtml();
        String convertByFile = excelToHtml.SubmitPost("http://dcs.yozosoft.com:80/upload", "C:/ftp"+addressurl, "0");
        System.out.println(convertByFile);
        String url= String.valueOf(convertByFile.subSequence(21,87));
        return  new Result(ResultStatusCode.OK,url);
    }
}

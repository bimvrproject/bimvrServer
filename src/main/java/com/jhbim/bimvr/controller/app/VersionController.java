package com.jhbim.bimvr.controller.app;

import com.jhbim.bimvr.dao.entity.pojo.Version;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.service.IVersionService;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${version}/version")
public class VersionController {

    @Resource
    IVersionService versionService;
    /*
    * 检查版本信息
    * 拿着版本号查询数据库
    * */
    @RequestMapping("/checkVersion")
    public Result checkVersion(){
       Version reVersion = versionService.checkVersion();
       return new Result(ResultStatusCode.OK,reVersion);
    }
}

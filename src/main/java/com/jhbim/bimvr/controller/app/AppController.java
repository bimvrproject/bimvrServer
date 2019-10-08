package com.jhbim.bimvr.controller.app;

import com.jhbim.bimvr.dao.entity.pojo.App;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.service.IAppService;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-09-29 13:23
 */
@RestController
@RequestMapping("/${version}/app")
public class AppController {

    @Autowired
    private IAppService iAppService;

    @GetMapping("/openApp")
    public Result openArc(Long projectId){
        App app = iAppService.selectByProjectId(projectId);
        return new Result(ResultStatusCode.OK,app);
    }

    @GetMapping("/allApp")
    public Result allApp(){
        List<App> select = iAppService.select();
        return new Result(ResultStatusCode.OK,select);
    }

}

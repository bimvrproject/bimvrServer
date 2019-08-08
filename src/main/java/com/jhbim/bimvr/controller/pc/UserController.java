package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${version}/pcUser")
public class UserController {

        /**
         *   根据token去获取用户信息
         * */
        @RequestMapping("/getUserByToken")
        public Result getUser(){
            User user = ShiroUtil.getUser();
            user.getCompanyId();
            //ceshi
            return  new Result(ResultStatusCode.OK,user);
        }

}

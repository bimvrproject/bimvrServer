package com.jhbim.bimvr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.service.IUserService;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.system.shiro.LoginType;
import com.jhbim.bimvr.system.shiro.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${version}/user")
public class LoginController {

    @Resource
    IUserService userService;

    /**
     * 用户密码登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String username, String password){
        UserToken token = new UserToken(LoginType.USER_PASSWORD, username, password);
        return shiroLogin(token);
    }

    /**
     * 手机验证码登录
     *      注：由于是demo演示，此处不添加发送验证码方法；
     *          正常操作：发送验证码至手机并且将验证码存放在redis中，登录的时候比较用户穿过来的验证码和redis中存放的验证码
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping("phoneLogin")
    public Result phoneLogin(String phone, String code){
        // 此处phone替换了username，code替换了password
        UserToken token = new UserToken(LoginType.USER_PHONE, phone, code);
        return shiroLogin(token);
    }

    /**
     * 微信登录
     *      注：由于是demo演示，此处只接收一个code参数（微信会生成一个code，然后通过code获取openid等信息）
     *          其他根据个人实际情况添加参数
     * @param code
     * @return
     */
    @RequestMapping("wechatLogin")
    public Result wechatLogin(String code){
        // 此处假装code分别是username、password
        UserToken token = new UserToken(LoginType.WECHAT_LOGIN, code, code, code);
        return shiroLogin(token);
    }

    public Result shiroLogin(UserToken token){
        try {
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (subject.isAuthenticated()) {
                JSON json = new JSONObject();
                ((JSONObject) json).put("token", subject.getSession().getId());

                return new Result(ResultStatusCode.OK, json);
            }else{
                return new Result(ResultStatusCode.SHIRO_ERROR);
            }
        }catch (IncorrectCredentialsException | UnknownAccountException e){
            e.printStackTrace();
            return new Result(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        }catch (LockedAccountException e){
            return new Result(ResultStatusCode.USER_FROZEN);
        }catch (Exception e){
            return new Result(ResultStatusCode.SYSTEM_ERR);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return new Result(ResultStatusCode.OK);
    }

    /**
     * 用户修改密码
     */
    @RequestMapping("/changePwd")
    public Result changePwd(String oldPwd, String newPwd) {
        int result = userService.updatePwd(oldPwd, newPwd);
        if (result==1) {
            return  new Result(ResultStatusCode.OK);
        }else {
            return new Result(ResultStatusCode.FAIL);
        }

    }

}

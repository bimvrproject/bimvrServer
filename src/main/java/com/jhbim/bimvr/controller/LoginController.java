package com.jhbim.bimvr.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.service.IUserService;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.system.shiro.LoginType;
import com.jhbim.bimvr.system.shiro.SMSConfig;
import com.jhbim.bimvr.system.shiro.UserToken;
import com.jhbim.bimvr.utils.MD5Util;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/${version}/user")
public class LoginController {
    @Resource
    IUserService userService;
    @Resource
    public RedisTemplate redisTemplate;
    @Resource
    UserMapper userMapper;
    /**
     * 用户密码登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String username, String password,HttpServletRequest request){
        //根据登录人的手机号查询他的所有的信息
        User user = userMapper.getByPhone(username);
        //判断是否注册过
        if(user==null){
            return new Result(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD); //登录失败
        }
        //登录成功
        ServletContext application=request.getSession().getServletContext();
        application.setAttribute("User_CompanyId",user.getCompanyId());
        application.setAttribute("User_Phone",user.getPhone());
//        Long usrcompanyid= (Long) application.getAttribute("User_CompanyId");
        //注册的时候将密码加密  登录的时候跟MD5做匹配  登录成功
        if(MD5Util.encrypt(password).equals(user.getPassword())){
            UserToken token = new UserToken(LoginType.USER_PASSWORD, username, password);
            return shiroLogin(token);
        }
        return new Result(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
    }

    /**
     * 手机验证码登录
     *      注：由于是demo演示，此处不添加发送验证码方法；
     *          正常操作：发送验证码至手机并且将验证码存放在redis中，登录的时候比较用户穿过来的验证码和redis中存放的验证码
     * @param
     * @param
     * @return
     */
    @RequestMapping("phoneLogin")
    public Result sendSms(String mobile){
        //随机数6
        String random= RandomStringUtils.randomNumeric(6);
        System.out.println(mobile+"随机数:"+random);
        redisTemplate.opsForValue().set(mobile,random+"",5, TimeUnit.MINUTES);  	//	5分钟过期
        SMSConfig.send(mobile,random);
        return new Result(ResultStatusCode.OK);

    }

    /**
     * 手机验证码注册
     * @param mobile
     * @return
     */
    @RequestMapping("RegisterphoneLogin")
    public Result RegisterphoneLogin(String mobile){
        String random= RandomStringUtils.randomNumeric(6);
        System.out.println(mobile+"随机数:"+random);
        redisTemplate.opsForValue().set(mobile,random+"",5, TimeUnit.MINUTES);  	//	5分钟过期
        SMSConfig.send(mobile,random);
        UserToken token = new UserToken(LoginType.USER_REGISTER, mobile, random);
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

    /**
     *  判断手机号是否存在
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/register")
    public Result register(String phone){
        User user = userMapper.getByPhone(phone);
        if(user!=null){
            return new Result(ResultStatusCode.FAIL, "已注册");
        }
        return new Result(ResultStatusCode.OK);
    }

    /**
     *  注册获取redis里面的验证码 判断是否一致 完成用户注册
     * @param smsCode  验证码
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/RegistercheckSmsCode")
    public Result checkSmsCode(String smsCode, String phone,String pwd){
        Result result = new Result();
        if(redisTemplate.opsForValue().get(phone)==null){
            result.setCode(1);
            result.setMsg("短信验证码输入超时!");
        }else{
            String code = redisTemplate.opsForValue().get(phone).toString();
            if(!code.equals(smsCode)){
                result.setCode(2);
                result.setMsg("短信验证码错误!");
            }else{
                result.setCode(0);
                result.setMsg("成功");
                User user=new User();
                user.setCompanyId(1L);
                user.setRoleId(3L);
                user.setPhone(phone);
                user.setPassword(MD5Util.encrypt(pwd));
                userMapper.insertSelective(user);
                return new Result(ResultStatusCode.OK,"保存成功");
            }
        }
        return result;
    }

    /**
     *  登录获取redis里面的验证码 判断是否一致 完成用户登录
     * @param smsCode 验证码
     * @param phone 手机号
     * @param request
     * @return
     */
    @RequestMapping("/LogincheckSmsCode")
    public Result LogincheckSmsCode(String smsCode, String phone, HttpServletRequest request){
        Result result = new Result();
        if(redisTemplate.opsForValue().get(phone)==null){
            result.setCode(1);
            result.setMsg("短信验证码输入超时!");
        }else{
            String code = redisTemplate.opsForValue().get(phone).toString();
            if(!code.equals(smsCode)){
                result.setCode(2);
                result.setMsg("短信验证码错误!");
            }else{
                result.setCode(0);
                result.setMsg("成功");
                User user = userMapper.getByPhone(phone);
                ServletContext application=request.getSession().getServletContext();
                application.setAttribute("User_CompanyId",user.getCompanyId());
                UserToken token = new UserToken(LoginType.USER_PHONE, phone, smsCode);
                return shiroLogin(token);
            }
        }

        return result;
    }

}

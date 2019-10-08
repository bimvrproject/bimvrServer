package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.User;

public interface IUserService {
    User findByUserName(String userName);

    User getByPhone(String phone);

    User getByOpenid(String openId);

    //修改用户密码
    int updatePwd(String oldPwd, String newPwd);
    /**
     *  用户注册
     *      kongweijia
     */
    int register(String username,String password);
}

package com.jhbim.bimvr.utils;

import com.jhbim.bimvr.dao.entity.pojo.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserName() {
        return ((User) SecurityUtils.getSubject().getPrincipal()).getUserName();
    }

    public static String getPassword() {
        return ((User) SecurityUtils.getSubject().getPrincipal()).getPassword();
    }

}

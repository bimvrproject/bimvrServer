package com.jhbim.bimvr.system.shiro;

import com.jhbim.bimvr.dao.entity.pojo.Permission;
import com.jhbim.bimvr.dao.entity.pojo.Role;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.service.IPermissionService;
import com.jhbim.bimvr.service.IRoleServie;
import com.jhbim.bimvr.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 统一角色授权控制策略
 */
@Slf4j
public class AuthorizationRealm extends AuthorizingRealm {

    public final static Logger log = LoggerFactory.getLogger(AuthorizationRealm.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleServie roleService;
    @Autowired
    private IPermissionService permission;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal instanceof User) {
            User userLogin = (User) principal;
            if(userLogin != null){
                userLogin = userService.findByUserName(userLogin.getUserName());
                Role role = roleService.selectByPrimaryKey(userLogin.getRoleId());
                if(role != null){
                    info.addRole(role.getRoleName());

                    List<Permission> permissionList = permission.getPermission(role.getPermission());
                    if(CollectionUtils.isNotEmpty(permissionList)){
                        for (Permission permission : permissionList){
                            if(StringUtils.isNoneBlank(permission.getPermission())){
                                info.addStringPermission(permission.getPermission());
                            }
                        }
                    }
                }
            }
        }
        log.info("---------------- 获取到以下权限 ----------------");
        log.info(info.getStringPermissions().toString());
        log.info("---------------- Shiro 权限获取成功 ----------------------");
        return info;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        return null;
    }
}

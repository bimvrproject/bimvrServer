package com.jhbim.bimvr.system.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 手机验证码注册realm
 */
@Slf4j
public class UserRegisterRealm extends AuthorizingRealm {

    public final static Logger log = LoggerFactory.getLogger(UserRegisterRealm.class);


    @Override
    public String getName() {
        return LoginType.USER_REGISTER.getType();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof UserToken) {
            return ((UserToken) token).getLoginType() == LoginType.USER_REGISTER;
        } else {
            return false;
        }
    }


    @Override
    public void setAuthorizationCacheName(String authorizationCacheName) {
        super.setAuthorizationCacheName(authorizationCacheName);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("---------------- 手机验证码注册 ----------------------");
        UserToken token = (UserToken) authcToken;
        // 手机验证码
        String validCode = String.valueOf(token.getPassword());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                "", //用户
                validCode, //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}

package com.jhbim.bimvr.system.config;

import com.jhbim.bimvr.system.shiro.*;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${version}")
    private String version;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 没有登陆的用户只能访问登陆页面，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/common/unauth");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/auth/index");
        // 未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("common/unauth");

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 权限控制map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 公共请求
//        filterChainDefinitionMap.put("/common/**", "anon");

//        filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/v1.0/version/checkVersion", "anon");

        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/"+version+"/userProject/getProject", "anon"); // 表示可以匿名访问
        // 账号密码登录方法
        filterChainDefinitionMap.put("/"+version+"/user/login", "anon"); // 表示可以匿名访问
        //手机验证码登录
        filterChainDefinitionMap.put("/"+version+"/user/phoneLogin", "anon"); // 表示可以匿名访问
        //手机验证码注册
        filterChainDefinitionMap.put("/"+version+"/user/RegisterphoneLogin", "anon"); // 表示可以匿名访问
        // 判断手机号是否已注册
        filterChainDefinitionMap.put("/"+version+"/user/register", "anon"); // 表示可以匿名访问
        // 注册判断手机号和验证码是否正确  完成注册
        filterChainDefinitionMap.put("/"+version+"/user/RegistercheckSmsCode", "anon"); // 表示可以匿名访问
        //登录判断手机号和验证码是否正确  完成登录
        filterChainDefinitionMap.put("/"+version+"/user/LogincheckSmsCode", "anon"); // 表示可以匿名访问
        //上传zip建筑方法
        filterChainDefinitionMap.put("/"+version+"/Upload/uploadCategory", "anon");
        //上传zip建筑图纸方法
        filterChainDefinitionMap.put("/"+version+"/Upload/uploadpicture", "anon");
        //上传zip管道方法
        filterChainDefinitionMap.put("/"+version+"/Upload/uploadpipe", "anon");
        //上传zip管道图纸方法
        filterChainDefinitionMap.put("/"+version+"/Upload/uploadpipepingmian", "anon");
        //绑定项目
        filterChainDefinitionMap.put("/"+version+"/pcproject/getProject", "anon");
        //上传建筑清单
        filterChainDefinitionMap.put("/"+version+"/Upload/uploadjzprice", "anon");
        //上传管道清单
        filterChainDefinitionMap.put("/"+version+"/Upload/uploadgxprice", "anon");
        //此处需要添加一个kickout，上面添加的自定义拦截器才能生效
        filterChainDefinitionMap.put("/"+version+"/**", "authc,kickout");// 表示需要认证才可以访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());

        List<Realm> realms = new ArrayList<>();
        // 统一角色权限控制realm
        realms.add(authorizingRealm());
        // 用户密码登录realm
        realms.add(userPasswordRealm());
        // 用户手机号验证码登录realm
        realms.add(userPhoneRealm());
        //用户手机号验证码注册
        realms.add(UserRegisterRealm());
        // 微信登录realm
        realms.add(wechatLoginRealm());

        securityManager.setRealms(realms);
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 自定义的Realm管理，主要针对多realm
     */
    @Bean("authModularRealmAuthenticator")
    public AuthModularRealmAuthenticator modularRealmAuthenticator() {
        AuthModularRealmAuthenticator customizedModularRealmAuthenticator = new AuthModularRealmAuthenticator();
        // 设置realm判断条件
        customizedModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());

        return customizedModularRealmAuthenticator;
    }

    @Bean
    public AuthorizingRealm authorizingRealm(){
        AuthorizationRealm authorizationRealm = new AuthorizationRealm();
        authorizationRealm.setName(LoginType.COMMON.getType());

        return authorizationRealm;
    }

    /**
     * 密码登录realm
     *
     * @return
     */
    @Bean
    public UserPasswordRealm userPasswordRealm() {
        UserPasswordRealm userPasswordRealm = new UserPasswordRealm();
        userPasswordRealm.setName(LoginType.USER_PASSWORD.getType());
        // 自定义的密码校验器
        userPasswordRealm.setCredentialsMatcher(credentialsMatcher());
        return userPasswordRealm;
    }

    /**
     * 手机号验证码登录realm
     * @return
     */
    @Bean
    public UserPhoneRealm userPhoneRealm(){
        UserPhoneRealm userPhoneRealm = new UserPhoneRealm();
        userPhoneRealm.setName(LoginType.USER_PHONE.getType());

        return userPhoneRealm;
    }

    /**
     * 手机号验证码注册
     * @return
     */
    @Bean
    public UserRegisterRealm UserRegisterRealm(){
        UserRegisterRealm userRegisterRealm = new UserRegisterRealm();
        userRegisterRealm.setName(LoginType.USER_REGISTER.getType());

        return userRegisterRealm;
    }

    /**
     * 微信授权登录realm
     * @return
     */
    @Bean
    public WechatLoginRealm wechatLoginRealm(){
        WechatLoginRealm wechatLoginRealm = new WechatLoginRealm();
        wechatLoginRealm.setName(LoginType.WECHAT_LOGIN.getType());

        return wechatLoginRealm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix("SPRINGBOOT_CACHE:");   //设置前缀
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix("SPRINGBOOT_SESSION:");
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public SessionManager sessionManager() {
        SimpleCookie simpleCookie = new SimpleCookie("Token");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(false);

        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }


    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(redisPort);
        redisManager.setTimeout(100*3); //设置过期时间 单位：秒 3600
        redisManager.setPassword(redisPassword);
        return redisManager;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public SessionControlFilter kickoutSessionControlFilter() {
        SessionControlFilter kickoutSessionControlFilter = new SessionControlFilter();
        kickoutSessionControlFilter.setCache(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(20); // 设置默认同时在线人数
        kickoutSessionControlFilter.setKickoutUrl("/"+version+"/**");
        return kickoutSessionControlFilter;
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     * 此方法需要用static作为修饰词，否则无法通过@Value()注解的方式获取配置文件的值
     *
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}

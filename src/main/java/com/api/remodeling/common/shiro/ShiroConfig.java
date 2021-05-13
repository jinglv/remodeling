package com.api.remodeling.common.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @author jinglv
 * @date 2021/01/17
 */
@Configuration
public class ShiroConfig {

    /**
     * Shiro过滤器，配置访问地址，拦截与被拦截的
     *
     * @param securityManager 安全管理器
     * @return 返回过滤完成的对象
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/find", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");

        // 过滤链定义，从上向下顺序执行
        // authc:url都必须认证通过才可以访问; anon:url都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
        shiroFilterFactoryBean.setLoginUrl("/user/unauth");
        return shiroFilterFactoryBean;
    }

    /**
     * 重新设置SecurityManager，通过自动以的MyRealm完成登录校验：
     *
     * @return
     */
    @Bean
    public RemodelingRealm remodelingRealm() {
        RemodelingRealm remodelingRealm = new RemodelingRealm();
        // 修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);
        remodelingRealm.setCredentialsMatcher(credentialsMatcher);
        return remodelingRealm;
    }

    /**
     * 安全管理器
     *
     * @param myRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(RemodelingRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //自定义session管理
        securityManager.setSessionManager(sessionManager());
        // 设置realm
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager manager = new CustomSessionManager();
        manager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return manager;
    }
}

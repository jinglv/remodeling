package com.api.remodeling.shiro;

import com.api.remodeling.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jinglv
 * @date 2020/09/19
 */
public class RemodelingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 管理授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 自定义登录校验//身份验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        // 认证逻辑
//        String username = authenticationToken.getPrincipal().toString();
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("username", username);
//        User dbUser = userService.selectOne(queryWrapper);
//        if (dbUser != null) {
//            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), getName());
//            return simpleAuthenticationInfo;
//        } else {
//            System.out.println("token为空");
//        }
        return null;
    }
}

package com.api.remodeling.common.shiro;

import com.api.remodeling.entity.User;
import com.api.remodeling.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 *
 * @author jinglv
 * @date 2021/01/17
 */
@Slf4j
public class RemodelingRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权管理
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
        // 认证逻辑
        String username = authenticationToken.getPrincipal().toString();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        User dbUser = userService.getOne(queryWrapper);
        if (dbUser != null) {
            return new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), getName());
        } else {
            log.error("token为空！");
        }
        return null;
    }
}

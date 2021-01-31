package com.api.remodeling.service;

import com.api.remodeling.entity.User;
import com.api.remodeling.vo.ResultVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jinglv
 * @since 2021-01-17
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param user 用户信息
     */
    ResultVo register(User user);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUserName(String username);

}

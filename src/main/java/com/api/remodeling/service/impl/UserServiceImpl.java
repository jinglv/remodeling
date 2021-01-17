package com.api.remodeling.service.impl;

import com.api.remodeling.entity.User;
import com.api.remodeling.mapper.UserMapper;
import com.api.remodeling.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jinglv
 * @since 2021-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

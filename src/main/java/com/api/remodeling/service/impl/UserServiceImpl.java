package com.api.remodeling.service.impl;

import com.api.remodeling.common.utils.SaltUtils;
import com.api.remodeling.entity.User;
import com.api.remodeling.mapper.UserMapper;
import com.api.remodeling.service.UserService;
import com.api.remodeling.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jinglv
 * @since 2021-01-17
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResultVo register(User user) {
        // 处理业务调用DAO
        // 1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        // 2.将随机盐保存到数据
        user.setSalt(salt);
        // 3.明文密码进行 md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        // 保存到数据库中
        int insertNumber = userMapper.insert(user);
        // 接口返回结果
        ResultVo resultVo = new ResultVo();
        resultVo.setResult("注册成功");
        return resultVo;
    }

    @Override
    public User findByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return userMapper.selectOne(queryWrapper);
    }
}

package com.api.remodeling.service.impl;

import com.api.remodeling.BaseTest;
import com.api.remodeling.entity.User;
import com.api.remodeling.service.UserService;
import com.api.remodeling.vo.ResultVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jinglv
 * @date 2021/5/13 12:38 下午
 */
class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;


    @Test
    void register() {
        User user = new User();
        user.setUsername("xiaohei")
                .setPassword("321321")
                .setEmail("xiaohei@qq.com")
                .setRegisterTime(LocalDateTime.now());
        ResultVo register = userService.register(user);
        assertAll("result",
                () -> assertEquals("注册成功", register.getResult())
        );
    }

    @Test
    void findByUserName() {
    }
}
package com.api.remodeling.controller;


import cn.hutool.json.JSONUtil;
import com.api.remodeling.common.response.ApiCodeEnum;
import com.api.remodeling.common.response.ApiResponse;
import com.api.remodeling.dto.user.LoginUserDto;
import com.api.remodeling.dto.user.RegisterUserDto;
import com.api.remodeling.entity.User;
import com.api.remodeling.service.UserService;
import com.api.remodeling.vo.ResultVo;
import com.api.remodeling.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jinglv
 * @since 2021-01-17
 */
@Slf4j
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册接口
     *
     * @param registerUserDto 注册用户信息
     * @return 返回接口结果
     */
    @PostMapping("register")
    @ApiOperation(value = "注册方法", httpMethod = "POST")
    public ApiResponse<ResultVo> register(@RequestBody RegisterUserDto registerUserDto) {
        log.info("用户注册请求参数：{}", JSONUtil.parse(registerUserDto));
        // 对象转换
        User user = new User();
        user.setUsername(registerUserDto.getUsername())
                .setPassword(registerUserDto.getPassword())
                .setEmail(registerUserDto.getEmail())
                .setRegisterTime(LocalDateTime.now());
        // 调用业务逻辑层方法，插入到到DB，目前只考虑正向情况后面会统一处理异常
        ResultVo register = userService.register(user);
        return ApiResponse.success(register);
    }

    /**
     * 用户登录接口
     *
     * @param user 登录用户信息
     * @return 返回接口结果
     */
    @PostMapping("login")
    @ApiOperation(value = "登录方法", httpMethod = "POST")
    public ApiResponse<UserVo> login(@RequestBody LoginUserDto user) {
        log.info("用户登录请求参数：{}", JSONUtil.parse(user));
        try {
            // 获取主体对象
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            // 获取当前的sessionId
            String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
            User loginUser = userService.findByUserName(subject.getPrincipal().toString());
            // 得到当前登录的用户id放到data中
            return ApiResponse.success(new UserVo().setUserId(loginUser.getId()).setSessionId(sessionId).setResult("用户登录成功"));
        } catch (IncorrectCredentialsException e) {
            log.error("用户登录密码错误:{}", e.getMessage());
            return ApiResponse.failure(ApiCodeEnum.FAIL, new UserVo().setResult("密码错误"));
        } catch (UnknownAccountException e) {
            log.error("用户登录用户名错误:{}", e.getMessage());
            return ApiResponse.failure(ApiCodeEnum.FAIL, new UserVo().setResult("用户名错误"));
        }
    }
}

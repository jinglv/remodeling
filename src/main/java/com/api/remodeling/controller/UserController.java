package com.api.remodeling.controller;


import cn.hutool.json.JSONUtil;
import com.api.remodeling.common.response.ApiCodeEnum;
import com.api.remodeling.common.response.ApiResponse;
import com.api.remodeling.entity.User;
import com.api.remodeling.service.UserService;
import com.api.remodeling.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jinglv
 * @since 2021-01-17
 */
@Slf4j
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
     * @param user 用户信息
     * @return 返回接口结果
     */
    @PostMapping("register")
    @ApiOperation(value = "注册方法", httpMethod = "POST")
    public ApiResponse<String> register(@RequestBody User user) {
        log.info("用户注册请求参数：{}", JSONUtil.parse(user));
        String result = null;
        // 调用业务逻辑层方法，插入到到DB，目前只考虑正向情况后面会统一处理异常
        boolean save = userService.save(user);
        if (save) {
            log.info("用户注册成功：{}", true);
            result = "注册成功";
        }
        return ApiResponse.success(result);
    }

    /**
     * 用户登录接口
     *
     * @param user 用户信息
     * @return 返回接口结果
     */
    @PostMapping("login")
    @ApiOperation(value = "登录方法", httpMethod = "POST")
    public ApiResponse<UserVo> login(@RequestBody User user) {
        log.info("用户登录请求参数：{}", JSONUtil.parse(user));
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            // 将sessionId返回去
            String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
            // 得到当前登录的用户id放到data中
            User loginUser = (User) subject.getPrincipal();
            return ApiResponse.success(new UserVo().setUserId(loginUser.getId()).setSessionId(sessionId).setResult("用户登录成功"));
        } catch (Exception e) {
            log.error("用户登录认证失败:{}", e.getMessage());
            return ApiResponse.failure(ApiCodeEnum.FAIL, new UserVo().setResult("用户登录失败"));
        }
    }
}

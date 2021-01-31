package com.api.remodeling.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jinglv
 * @date 2021/01/31
 */
@Data
@ApiModel(value = "注册用户对象", description = "用户对象user")
public class RegisterUserDto implements Serializable {
    private static final long serialVersionUID = 5859437718442735447L;

    /**
     * 接口传入的注册用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 接口传入的注册密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 接口传入的注册邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
}

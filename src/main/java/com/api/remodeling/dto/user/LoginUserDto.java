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
@ApiModel(value = "登录用户对象", description = "用户对象user")
public class LoginUserDto implements Serializable {

    private static final long serialVersionUID = -5424436762728692035L;

    /**
     * 接口传入的登录用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 接口传入的登录密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}

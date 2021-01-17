package com.api.remodeling.common.response;

import lombok.Getter;

/**
 * 定义状态码枚举值
 *
 * @author jingLv
 * @date 2020/09/16
 */
@Getter
public enum ApiCodeEnum {
    /**
     * 成功
     */
    SUCCESS("00000", "ok"),
    /**
     * 参数非法
     */
    ILLEGAL_ARGUMENT("10000", "参数非法"),
    /**
     * 失败
     */
    FAIL("00001", "fail"),

    /**
     * 重复请求
     */
    REPEAT("00010", "重复请求");

    /**
     * 响应编码
     */
    private final String code;
    /**
     * 响应描述
     */
    private final String message;

    /**
     * 常量枚举，不能外部实例化
     *
     * @param code    响应编码
     * @param message 响应描述
     */
    ApiCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

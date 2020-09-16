package com.api.remodeling.common.api;

import lombok.Getter;
import lombok.ToString;

/**
 * 定义返回体类
 *
 * @author jingLv
 * @date 2020/09/16
 */
@Getter
@ToString
public class ApiResponse<T> {
    /**
     * 响应编码
     */
    private String code;
    /**
     * 响应描述
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 构造函数
     *
     * @param codeEnum 响应枚举内容
     */
    public ApiResponse(ApiCodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.data = data;
    }


    /**
     * 业务成功返回代码和描述信息
     */
    public static ApiResponse<Void> success() {
        return new ApiResponse<Void>(ApiCodeEnum.SUCCESS, null);
    }

    /**
     * 业务成功返回代码,描述和返回参数
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(ApiCodeEnum.SUCCESS, data);
    }

    /**
     * 业务成功返回代码,描述和返回参数
     */
    public static <T> ApiResponse<T> success(ApiCodeEnum codeEnum, T data) {
        if (codeEnum == null) {
            return success(data);
        }
        return new ApiResponse<T>(ApiCodeEnum.SUCCESS, data);
    }

    /**
     * 业务异常返回代码和描述信息
     */
    public static <T> ApiResponse<T> failure() {
        return new ApiResponse<T>(ApiCodeEnum.FAIL, null);
    }

    /**
     * 业务异常返回代码,描述和返回的参数
     */
    public static <T> ApiResponse<T> failure(ApiCodeEnum codeEnum) {
        return failure(codeEnum, null);
    }

    /**
     * 业务异常返回业务代码，描述和返回的参数
     */
    public static <T> ApiResponse<T> failure(ApiCodeEnum codeEnum, T data) {
        if (codeEnum == null) {
            return new ApiResponse<T>(ApiCodeEnum.FAIL, null);
        }
        return new ApiResponse<T>(codeEnum, data);
    }
}

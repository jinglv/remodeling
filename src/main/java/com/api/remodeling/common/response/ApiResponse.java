package com.api.remodeling.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "API接口统一JSON格式返回")
public class ApiResponse<T> {
    /**
     * 响应编码
     */
    @ApiModelProperty(value = "响应编码", example = "00000 or 00001", notes = "响应编码")
    private final String code;
    /**
     * 响应描述
     */
    @ApiModelProperty(value = "响应描述", example = "ok or fail", notes = "响应描述")
    private final String message;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "json", example = "", notes = "响应对象")
    private final T data;

    /**
     * 构造函数
     *
     * @param apiCodeEnum 响应枚举内容
     */
    public ApiResponse(ApiCodeEnum apiCodeEnum, T data) {
        this.code = apiCodeEnum.getCode();
        this.message = apiCodeEnum.getMessage();
        this.data = data;
    }


    /**
     * 业务成功返回代码和描述信息
     */
    public static ApiResponse<Void> success() {
        return new ApiResponse<>(ApiCodeEnum.SUCCESS, null);
    }

    /**
     * 业务成功返回代码,描述和返回参数
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiCodeEnum.SUCCESS, data);
    }

    /**
     * 业务成功返回代码,描述和返回参数
     */
    public static <T> ApiResponse<T> success(ApiCodeEnum apiCodeEnum, T data) {
        if (apiCodeEnum == null) {
            return success(data);
        }
        return new ApiResponse<>(ApiCodeEnum.SUCCESS, data);
    }

    /**
     * 业务异常返回代码和描述信息
     */
    public static <T> ApiResponse<T> failure() {
        return new ApiResponse<>(ApiCodeEnum.FAIL, null);
    }

    /**
     * 业务异常返回代码,描述和返回的参数
     */
    public static <T> ApiResponse<T> failure(ApiCodeEnum apiCodeEnum) {
        return failure(apiCodeEnum, null);
    }

    /**
     * 业务异常返回业务代码，描述和返回的参数
     */
    public static <T> ApiResponse<T> failure(ApiCodeEnum apiCodeEnum, T data) {
        if (apiCodeEnum == null) {
            return new ApiResponse<>(ApiCodeEnum.FAIL, null);
        }
        return new ApiResponse<>(apiCodeEnum, data);
    }
}

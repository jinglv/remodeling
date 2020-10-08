package com.api.remodeling.common.exception;

import com.api.remodeling.common.response.ApiCodeEnum;
import lombok.Getter;

/**
 * @author jingLv
 * @date 2020/09/16
 */
@Getter
public class ApiResultException extends Exception {
    /**
     * 业务异常信息
     */
    ApiCodeEnum apiCodeEnum;

    public ApiResultException() {
        this(ApiCodeEnum.FAIL);
    }

    public ApiResultException(ApiCodeEnum apiCodeEnum) {
        super(apiCodeEnum.getMessage());
        this.apiCodeEnum = apiCodeEnum;
    }
}

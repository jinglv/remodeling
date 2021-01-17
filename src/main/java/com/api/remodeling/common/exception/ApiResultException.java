package com.api.remodeling.common.exception;

import com.api.remodeling.common.response.ApiCodeEnum;
import lombok.Getter;

/**
 * @author jingLv
 * @date 2020/09/16
 */
@Getter
public class ApiResultException extends Exception {
    private final String code;

    public ApiResultException() {
        super();
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public ApiResultException(String message) {
        super(message);
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public ApiResultException(Throwable cause) {
        super(cause);
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public ApiResultException(String message, Throwable cause) {
        super(message, cause);
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public ApiResultException(String code, String message) {
        this(code, message, null);
    }

    public ApiResultException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}

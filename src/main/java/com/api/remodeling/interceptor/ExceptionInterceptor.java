package com.api.remodeling.interceptor;

import com.api.remodeling.common.exception.ApiResultException;
import com.api.remodeling.common.response.ApiCodeEnum;
import com.api.remodeling.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常增强使用
 *
 * @author jinglv
 * @date 2021/01/17
 */
@Slf4j
@Component
@ControllerAdvice(basePackages = "com.api.remodeling.controller")
public class ExceptionInterceptor {

    /**
     * 拦截业务类异常
     *
     * @param e ApiResultException
     * @return 返回异常结果
     */
    @ResponseBody
    @ExceptionHandler(ApiResultException.class)
    public ApiResponse<String> exceptionHandler(ApiResultException e) {
        log.error("捕捉业务类异常:" + e);
        return ApiResponse.failure(ApiCodeEnum.FAIL, e.getMessage());
    }

    /**
     * 拦截运行类异常
     *
     * @param e RuntimeException
     * @return 返回异常结果
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<String> runtimeException(RuntimeException e) {
        log.error("捕捉运行时异常：" + e);
        return ApiResponse.failure(ApiCodeEnum.FAIL, e.getMessage());
    }

    /**
     * 拦截类Throwable异常
     *
     * @param th Throwable
     * @return 返回异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ApiResponse<String> throwableHandle(Throwable th) {
        log.error("捕捉Throwable异常：", th);
        return ApiResponse.failure(ApiCodeEnum.FAIL, th.getMessage());
    }
}

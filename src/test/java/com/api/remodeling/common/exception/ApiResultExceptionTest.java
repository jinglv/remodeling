package com.api.remodeling.common.exception;

import com.api.remodeling.common.api.ApiCodeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jingLv
 * @date 2020/09/17
 */
class ApiResultExceptionTest {
    private static final ApiCodeEnum TEST_CODE = ApiCodeEnum.ILLEGAL_ARGUMENT;

    /**
     * public ApiResultException()
     */
    @Test
    void getCodeNoArgs() {
        ApiResultException apiResultException = new ApiResultException();
        Assertions.assertEquals(ApiCodeEnum.FAIL, apiResultException.getApiCodeEnum());
    }

    /**
     * public ApiResultException(ApiCodeEnum apiCodeEnum)
     */
    @Test
    void getCodeWithCode() {
        ApiResultException apiResultException = new ApiResultException(TEST_CODE);
        Assertions.assertEquals(TEST_CODE, apiResultException.getApiCodeEnum());
        Assertions.assertEquals(TEST_CODE.getMessage(), apiResultException.getMessage());
    }
}
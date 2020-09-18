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

    @Test
    void getApiCodeEnum() {
        ApiResultException apiResultException = new ApiResultException();
        Assertions.assertEquals(ApiCodeEnum.FAIL, apiResultException.getApiCodeEnum());
    }
}
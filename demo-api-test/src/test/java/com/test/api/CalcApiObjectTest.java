package com.test.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jingLv
 * @date 2020/10/23
 */
class CalcApiObjectTest {

    private Integer a = 1;
    private Integer b = 2;
    private static String token;

    @BeforeAll
    static void getToken() {
        token = TokenHelper.getAccessToken();
    }

    @Test
    void calcAdd() {
        Response addResponse = CalcApiObject.calcAdd(a, b, token);
        assertAll("result assertions",
                () -> assertEquals(addResponse.path("code"), "00000"),
                () -> assertEquals((Integer) addResponse.path("data"), 3)
        );
    }
}
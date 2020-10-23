package com.test.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * @author jingLv
 * @date 2020/10/23
 */
public class CalcApiObject {

    /**
     * 加法计算
     *
     * @param a 数值
     * @param b 数值
     * @param token token
     * @return 返回结果
     */
    public static Response calcAdd(Integer a, Integer b, String token) {
        return given()
                .header("token", token)
                .param("a", a)
                .param("b", b)
                .when()
                .get("http://localhost:8989/v1/calc/add")
                .then()
                .log().body()
                .extract().response();
    }
}

package com.test.api;

import static io.restassured.RestAssured.given;

/**
 * 获取token工具类
 *
 * @author jingLv
 * @date 2020/10/23
 */
public class TokenHelper {

    public static String getAccessToken() {
        String body = "{\n" +
                "    \"username\":\"xiaohong\",\n" +
                "    \"password\":\"123123\"\n" +
                "}";
        return given()
                .contentType("application/json")
                .body(body)
                .when()
                .get("http://localhost:8989/v1/user/login")
                .then().log().body()
                .extract().response()
                .path("data.token");
    }
}

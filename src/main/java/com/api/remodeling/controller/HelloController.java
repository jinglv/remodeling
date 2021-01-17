package com.api.remodeling.controller;

import com.api.remodeling.common.api.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jingLv
 * @date 2020/09/16
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/result")
    @ResponseBody
    public ApiResponse<Map<String, Object>> helloResult() {
        return ApiResponse.success(INFO);
    }
}
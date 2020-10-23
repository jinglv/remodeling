package com.api.remodeling.controller;

import com.api.remodeling.common.api.ApiResponse;
import com.api.remodeling.common.exception.ApiResultException;
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
@RequestMapping("/result")
@ResponseBody
public class ResultController {

    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<String, Object>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    public HashMap<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/result")
    public ApiResponse<Map<String, Object>> helloResult() {
        return ApiResponse.success(INFO);
    }

    @GetMapping("/helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("/helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new ApiResultException();
    }
}

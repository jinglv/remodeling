package com.api.remodeling.common.response;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 使用这个注解就可以返回统一的json格式,  用于类和方法上
 *
 * @author jingLv
 * @date 2020/09/16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseApiBody {
}

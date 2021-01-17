package com.api.remodeling.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户接口返回显示
 *
 * @author jinglv
 * @date 2021/01/17
 */
@Data
@Accessors(chain = true)
public class UserVo implements Serializable {

    private static final long serialVersionUID = 2670206806633614733L;

    /**
     * 用户userId
     */
    private Integer userId;

    /**
     * 用户sessionId
     */
    private String sessionId;

    /**
     * 接口结果
     */
    private String result;
}

package com.api.remodeling.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 测试接口表
 * </p>
 *
 * @author jinglv
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Api implements Serializable {

    private static final long serialVersionUID = -9084394612529997753L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 接口分类编号
     */
    private Integer apiClassificationId;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 接口请求方法
     */
    private String apiMethod;

    /**
     * 接口请求地址
     */
    private String apiUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}

package com.api.remodeling.service.impl;

import com.api.remodeling.entity.Api;
import com.api.remodeling.mapper.ApiMapper;
import com.api.remodeling.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试接口表 服务实现类
 * </p>
 *
 * @author jinglv
 * @since 2021-01-24
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {

}

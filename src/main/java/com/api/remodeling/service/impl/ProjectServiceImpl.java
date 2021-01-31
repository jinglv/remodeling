package com.api.remodeling.service.impl;

import com.api.remodeling.entity.Project;
import com.api.remodeling.mapper.ProjectMapper;
import com.api.remodeling.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author jinglv
 * @since 2021-01-24
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

}

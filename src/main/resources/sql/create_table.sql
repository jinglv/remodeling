DROP DATABASE IF EXISTS `remodeling`;
CREATE DATABASE `remodeling` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# test_user用户表
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user`
(
    `id`            INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name`     VARCHAR(50) NOT NULL COMMENT '用户名',
    `pass_word`     VARCHAR(32) NOT NULL COMMENT '密码',
    `email`         VARCHAR(40) DEFAULT NULL COMMENT '邮箱',
    `register_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_time`   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户表';

# test_project 项目表
DROP TABLE IF EXISTS `test_project`;
CREATE TABLE `test_project`
(
    `id`           INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `project_name` VARCHAR(50) NOT NULL COMMENT '项目名称',
    `host_name`    VARCHAR(225) DEFAULT NULL COMMENT '主机地址',
    `description`  VARCHAR(225) DEFAULT NULL COMMENT '描述',
    `create_user`  VARCHAR(50)  DEFAULT NULL COMMENT '创建人',
    `create_time`  DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`  DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='项目表';

# test_case测试用例表
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case`
(
    `id`          INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `suite_id`    INT         NOT NULL COMMENT '测试套件id',
    `case_name`   VARCHAR(50) NOT NULL COMMENT '测试用例名称',
    `create_user` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试用例表';

# test_case_param_value 测试用例参数表
DROP TABLE IF EXISTS `test_case_param_value`;
CREATE TABLE `test_case_param_value`
(
    `id`                      INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `case_id`                 INT          NOT NULL COMMENT '测试用例id',
    `api_request_param_id`    INT          NOT NULL COMMENT '关联的接口参数字段id',
    `api_request_param_value` VARCHAR(225) NOT NULL COMMENT '给参数字段准备的测试数据',
    `create_user`             VARCHAR(50) DEFAULT NULL COMMENT '创建人',
    `create_time`             DATETIME     NOT NULL COMMENT '创建时间',
    `update_time`             DATETIME     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试用例参数表';

# test_host HOST表
DROP TABLE IF EXISTS `test_host`;
CREATE TABLE `test_host`
(
    `id`          INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `project_id`  INT         NOT NULL COMMENT '项目id',
    `host_name`   VARCHAR(50) NOT NULL COMMENT 'host地址名',
    `description` VARCHAR(225) DEFAULT NULL COMMENT '描述',
    `create_user` VARCHAR(50)  DEFAULT NULL COMMENT '创建人',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='HOST表';

# test_suite 测试套件表
DROP TABLE IF EXISTS `test_suite`;
CREATE TABLE `test_suite`
(
    `id`          INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `project_id`  INT         NOT NULL COMMENT '项目id',
    `suite_name`  VARCHAR(50) NOT NULL COMMENT '测试套件名称',
    `description` VARCHAR(225) DEFAULT NULL COMMENT '描述',
    `create_user` VARCHAR(50)  DEFAULT NULL COMMENT '创建人',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试套件表';

# test_api 测试接口表
DROP TABLE IF EXISTS `test_api`;
CREATE TABLE `test_api`
(
    `id`                    INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `api_classification_id` INT         NOT NULL COMMENT '接口分类编号',
    `api_name`              VARCHAR(50) NOT NULL COMMENT '接口名称',
    `api_method`            VARCHAR(50)  DEFAULT NULL COMMENT '接口请求方法',
    `api_url`               VARCHAR(225) DEFAULT NULL COMMENT '接口请求地址',
    `description`           VARCHAR(225) DEFAULT NULL COMMENT '描述',
    `create_user`           VARCHAR(50)  DEFAULT NULL COMMENT '创建人',
    `create_time`           DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`           DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试接口表';

# test_api_classification 测试接口分类表
DROP TABLE IF EXISTS `test_api_classification`;
CREATE TABLE `test_api_classification`
(
    `id`                      INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `project_id`              INT         NOT NULL COMMENT '项目id',
    `api_classification_name` VARCHAR(50) NOT NULL COMMENT '接口分类名称',
    `description`             VARCHAR(225) DEFAULT NULL COMMENT '描述',
    `create_user`             VARCHAR(50)  DEFAULT NULL COMMENT '创建人',
    `create_time`             DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`             DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试接口分类表';

# test_api_request_param 测试接口请求参数表
DROP TABLE IF EXISTS `test_api_request_param`;
CREATE TABLE `test_api_request_param`
(
    `id`             INT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `api_id`         INT         NOT NULL COMMENT '关联的接口编号',
    `api_param_name` VARCHAR(50) NOT NULL COMMENT '接口参数名称',
    `api_param_type` VARCHAR(50) NOT NULL COMMENT '接口参数字段类型',
    `api_type`       VARCHAR(50) NOT NULL COMMENT '参数类型（1：query参数；2：body参数；3：header；4：body里的json参数）',
    `example_data`   VARCHAR(50) NOT NULL COMMENT '接口参数示例',
    `description`    VARCHAR(225) DEFAULT NULL COMMENT '描述',
    `create_time`    DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`    DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试接口请求参数表';

# test_rule 测试规则表
DROP TABLE IF EXISTS `test_rule`;
CREATE TABLE `test_rule`
(
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `case_id`     INT          NOT NULL COMMENT '测试用例编号',
    `rule`        VARCHAR(50)  NOT NULL COMMENT '验证方式',
    `expression`  VARCHAR(225) NOT NULL COMMENT '验证表达式',
    `operator`    VARCHAR(225) NOT NULL COMMENT '匹配规则',
    `expected`    VARCHAR(225) NOT NULL COMMENT '期望值',
    `create_user` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试规则表';

# test_report 测试报告表
DROP TABLE IF EXISTS `test_report`;
CREATE TABLE `test_report`
(
    `id`               INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `case_id`          INT          NOT NULL COMMENT '测试用例编号',
    `request_url`      VARCHAR(50)  NOT NULL COMMENT '请求地址',
    `request_headers`  VARCHAR(225) NOT NULL COMMENT '请求头信息',
    `request_body`     VARCHAR(225) NOT NULL COMMENT '请求主体数据',
    `response_headers` VARCHAR(225) NOT NULL COMMENT '响应头信息',
    `response_body`    VARCHAR(225) NOT NULL COMMENT '响应主体数据',
    `result`           VARCHAR(50)  NOT NULL COMMENT '测试结果',
    `create_time`      DATETIME     NOT NULL COMMENT '创建时间',
    `update_time`      DATETIME     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='测试报告表';

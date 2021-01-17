package com.api.remodeling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 *
 * @author lvjing
 * @date 2020/09/09
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.api.remodeling.mapper")
@EnableTransactionManagement
public class RemodelingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemodelingApplication.class, args);
    }

}

package com.api.remodeling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lvjing
 * @date 2020/09/09
 */
@SpringBootApplication
@MapperScan("com.api.remodeling.mapper")
public class RemodelingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemodelingApplication.class, args);
    }

}

package com.api.remodeling.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * knife4j的Swagger的配置
 *
 * @author jinglv
 * @date 2020/09/19
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    /**
     * 默认API，包括controller下的所有的类
     *
     * @return 文档信息
     */
    @Bean(value = "defaultApi2")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.remodeling.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 获取api信息
     *
     * @return api的介绍信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Remodeling的接口文档")
                .description("接口文档")
                .termsOfServiceUrl("http://xxx:8888/doc.html")
                .contact(new Contact("Jing", null, "xxxx@email.com"))
                .version("1.0")
                .build();
    }
}

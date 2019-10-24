package com.roc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/24 20:20
 * @description swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .pathMapping("/")
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.roc.api"))
                    .paths(PathSelectors.any())
                    .build().apiInfo(new ApiInfoBuilder()
                            .title("LbsServer的在线接口文档")
                            .description("方便开发测试接口~")
                            .version("1.0")
                            .contact(new Contact("Roc","https://github.com","zkp9701@163.com"))
                            .license("The Apache License")
                            .licenseUrl("https://www.bilibili.com")
                            .build());

    }

}

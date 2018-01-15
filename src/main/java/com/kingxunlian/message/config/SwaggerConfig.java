package com.kingxunlian.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置类
 *
 * @author yorke
 * @date 2016/7/27.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar1 = new ParameterBuilder();
        ParameterBuilder tokenPar3= new ParameterBuilder();
        ParameterBuilder tokenPar2= new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar1.name("X-KXL-Company-ID").description("令牌-公司id").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        tokenPar2.name("X-KXL-User-ID").description("令牌-用户id").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        tokenPar3.name("X-KXL-Department-ID").description("令牌-部门id").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar1.build());
        pars.add(tokenPar2.build());
        pars.add(tokenPar3.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kingxunlian"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Message Service API")
                .description("Message Service API")
                .termsOfServiceUrl("xltec.cc")
                .version("1.0")
                .build();
    }

}

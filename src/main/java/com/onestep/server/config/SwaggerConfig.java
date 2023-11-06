package com.onestep.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title("한 걸음")
                .description("가족간의 대화를 위한 앱, 한 걸음의 API 문서입니다.");

        return new OpenAPI()
                .info(info);
    }
}

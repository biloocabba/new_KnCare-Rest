package com.knits.kncare.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi {

    @Bean
    public OpenAPI customOpenAPI(@Value("v3") String appVersion) {
        return new OpenAPI().info(new Info().title("Controller API")
                .version(appVersion)
                .description("This is an api for KnCare App.")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("KnCare 1.0")
                        .url("http://springdoc.org")));
    }
}

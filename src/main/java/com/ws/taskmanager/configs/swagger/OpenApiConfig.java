package com.ws.taskmanager.configs.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title("Task Manager - REST API")
                        .version("v1")
                        .contact(new Contact().url("https://www.linkedin.com/in/joaopaulo-chaves/").email("jpcchaves@outlook.com"))
                        .description("REST API built to manage a to do application made with Spring Boot")
                        .termsOfService("https://porfolio-jpcchaves.vercel.app/")
                        .license(new License().name("MIT").url("https://porfolio-jpcchaves.vercel.app/")));
    }
}


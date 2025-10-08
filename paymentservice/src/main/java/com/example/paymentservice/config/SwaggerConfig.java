package com.example.paymentservice.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title("Payment Service API").
                        description("Documentation for the Payment Service API")
                        .version("1.0")
        ).components(new Components()
                .addParameters("userIdHeader",
                        new Parameter()
                                .in("header")
                                .required(true)
                                .name("userID")
                                .description("ID of the user, must be sent in every request")
                                .schema(new StringSchema().example("101"))));
    }
}

package com.lcwd.user.service.config;
//
//import java.util.List;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.servers.Server;
//
//@Configuration
//public class OpenAPIConfig {
//
//
//    @Bean
//    public OpenAPI myOpenAPI() {
//        return new OpenAPI().addSecurityItem(new SecurityRequirement()
//                        .addList("Bearer Authentication"))
//                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
//                .info(new Info().title("My REST API")
//                        .description("Some custom description of API.")
//                        .version("1.0")
//                        .contact(new Contact().name("Sallo Szrajbman")
//                                .email("www.baeldung.com").url("salloszraj@gmail.com"))
//                        .license(new License().name("License of API")
//                                .url("API license URL")));
//    }
//
//    private SecurityScheme createAPIKeyScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }
//}


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
@OpenAPIDefinition
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI baseOpenAPI(){
        ApiResponse badRequest = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 400, \"status\" : \"Bad Request\", \"Message\" : \"Bad Request\"}"))));
        ApiResponse internalServerError = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 500, \"status\" : \"internalServerError\", \"Message\" : \"internalServerError\"}"))));
        ApiResponse successfulResponse = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"name\":\"string\",\"surname\":\"string\",\"age\":0}"))));
        Components components = new Components();
        components.addResponses("badRequest",badRequest);
        components.addResponses("internalServerError",internalServerError);
        components.addResponses("successfulResponse",successfulResponse);
        return new OpenAPI().components(components).info(new Info().title("SpringBoot_Swagger Project OpenAPI Docs").version("1.0.0").description("Doc Description"));
    }
}
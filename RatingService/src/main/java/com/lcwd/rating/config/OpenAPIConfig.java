//package com.lcwd.rating.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class OpenAPIConfig {
//
//    @Value("${swagger.openapi.Rating-dev-url}")
//    private String devUrl;
//
//    @Bean
//    public OpenAPI myOpenAPI() {
//        Server devServer = new Server();
//        devServer.setUrl(devUrl);
//        devServer.setDescription("Server URL in Development environment");
//
//        Server prodServer = new Server();
//        //prodServer.setUrl(prodUrl);
//        prodServer.setDescription("Server URL in Production environment");
//
//        Info info = new Info()
//                .title("Tutorial Management API")
//                .version("1.0")
//                .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.bezkoder.com/terms");
//
//        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
//    }
//}
package com.api.wishlist.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo("MyApp Rest APIs",
//                "APIs for MyApp.",
//                "1.0",
//                "Terms of service",
//                new Contact("test", "www.org.com", "test@emaildomain.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList());
//    }


    @Bean
    public Docket swaggerWishlistApiV1() {
        return getDocket("v1");
    }

    @Bean
    public Docket swaggerWishlistApiV2() {
        return getDocket("v2");
    }

    private static Docket getDocket(final String version) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("wishlist-api-" + version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.wishlist.controller.api." + version))
                .paths(PathSelectors.ant("/api/" + version + "/*"))
                .build()
                .apiInfo(getBuild(version));
    }

    private static ApiInfo getBuild(final String version) {
        return new ApiInfoBuilder()
                .version(version)
                .title("Wishlist API")
                .description("Documentation Wishlist API" + version)
                .license("")
                .contact(new Contact("Wish List API", "", ""))
                .build();
    }
}
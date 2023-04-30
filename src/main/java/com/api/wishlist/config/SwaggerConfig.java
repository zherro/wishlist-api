package com.api.wishlist.config;

import java.util.Collections;
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
public class SwaggerConfig implements SwaggerAuthConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     *  gera docket default que mostrar todos os endpoints disponiveis na api
     * @return Docket
     */
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2);
//    }

    @Bean
    public Docket swaggerWishlistApiV1() {
        return getDocket("v1");
    }

    @Bean
    public Docket swaggerWishlistApiV2() {
        return getDocket("v2");
    }

    private Docket getDocket(final String version) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("wishlist-api-" + version)
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.wishlist.controller.api." + version))
                .paths(PathSelectors.ant("/api/" + version + "/**"))
                .build()
                .apiInfo(getBuild(version));
    }

    private ApiInfo getBuild(final String version) {
        return new ApiInfoBuilder()
                .version(version)
                .title("Wishlist API")
                .description("Documentation Wishlist API " + version)
                .license("")
                .licenseUrl("")
                .contact(new Contact("Wish List API", "", ""))
                .build();
    }
}
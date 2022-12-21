//
//package com.example.ApiTourist.model;
//
//
//
//import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.ApiTourist"))
//                .paths(PathSelectors.any())
//                .build().apiInfo(metaData());
//    }
//
//    private ApiInfo metaData() {
//        return new ApiInfoBuilder()
//                .title("ApiTourist interface - Spring Boot Swagger Configuration")
//                .description("\"Configuration de Swagger pour l'application ApiTourist \"")
//                .version("1.1.0")
//                .license("Apache 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
//                .contact(new Contact("Api by FC", "https://github.com/FatouDev02", "fatoumatacoulibaly4155@gmail.com"))
//                .build();
//    }
//    //for Swagger api doc generation
//    //http://localhost:8091/v2/api-docs
//}

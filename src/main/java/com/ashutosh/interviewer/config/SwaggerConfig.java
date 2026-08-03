package com.ashutosh.interviewer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI campaignOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Online Misinformation Awareness Campaign API")
                        .description("REST API documentation for retrieving awareness modules and collecting survey analytics.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Ashutosh Agrawal")
                                .email("developer@interviewer.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
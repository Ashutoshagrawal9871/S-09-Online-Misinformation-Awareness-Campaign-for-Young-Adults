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
                        .title("TruthLens — Online Misinformation Awareness Campaign API")
                        .description("REST API for the SIFT fact-check engine, awareness modules, surveys, workshops, and campaign analytics. Part of CUSoC project S-09.")
                        .version("v1.2.0")
                        .contact(new Contact()
                                .name("Ashutosh Agrawal")
                                .url("https://github.com/Ashutoshagrawal9871/S-09-Online-Misinformation-Awareness-Campaign-for-Young-Adults"))
                        .license(new License().name("CC BY-SA 4.0").url("https://creativecommons.org/licenses/by-sa/4.0/")));
    }
}

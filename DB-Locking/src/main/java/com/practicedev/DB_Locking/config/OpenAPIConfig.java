package com.practicedev.DB_Locking.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI movieTicketBookingServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Ticket Service API ")
                        .description("This is the REST API for Product Service")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Movie Ticket booking service WIKI Doc")
                        .url("https://movie-ticket-dummy-url.com/docs"));

    }

}

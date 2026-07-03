package br.com.maricotadoces.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info().title("Maricota Doces REST API")
                .description("API responsável pelo sistema Maricota Doces").version("1.0.0")
                .license(new License().name("Apache License Version 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}

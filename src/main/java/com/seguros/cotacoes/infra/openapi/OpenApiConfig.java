package com.seguros.cotacoes.infra.openapi;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Cotações de Seguro Prestamista",
        version = "v1",
        description = "API para cálculo e gestão de cotações (prêmio, corretagem, total e parcelamento).",
        contact = @Contact(name = "André Ferreira", email = "andre.c.ferreira-silva@itau-unibanco.com.br")
    )
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {

    // Força server relativo: Swagger usa a própria origem (host/porta/esquema atuais)
    @Bean
    public OpenAPI openAPI() {
        Server relative = new Server().url("/").description("Relative to current origin");
        return new OpenAPI().servers(List.of(relative));
    }
}
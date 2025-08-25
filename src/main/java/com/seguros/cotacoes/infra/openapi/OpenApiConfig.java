package com.seguros.cotacoes.infra.openapi;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
// REMOVIDO: import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Cotações de Seguro Prestamista",
        version = "v1",
        description = "API para cálculo e gestão de cotações (prêmio, corretagem, total e parcelamento).",
        contact = @Contact(name = "André Ferreira", email = "andre.c.ferreira-silva@itau-unibanco.com.br")
    )
    // REMOVIDO: servers = {...}
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig { }
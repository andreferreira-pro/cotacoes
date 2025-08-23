// LoginRequest.java
package com.seguros.cotacoes.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginRequest")
public record LoginRequest(
    @Schema(example = "admin") String username,
    @Schema(example = "admin") String password
) {}

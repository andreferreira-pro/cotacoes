// TokenResponse.java
package com.seguros.cotacoes.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TokenResponse")
public record TokenResponse(
    @Schema(example = "Bearer eyJhbGciOiJIUzI1NiJ9...") String token
) {}

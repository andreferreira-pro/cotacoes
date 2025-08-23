package com.seguros.cotacoes.infra.exception;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ErrorResponse", description = "Modelo padrão de erro da API")
public class ErrorResponse {
    @Schema(example = "2025-08-22T19:59:59.999Z")
    public String timestamp;
    @Schema(example = "400") public int status;
    @Schema(example = "Validation Error") public String error;
    @Schema(example = "Dados inválidos") public String message;
    @Schema(description = "Erros por campo (quando houver)")
    public Map<String, String> fields;
}

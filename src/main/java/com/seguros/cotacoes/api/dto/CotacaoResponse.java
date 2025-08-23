package com.seguros.cotacoes.api.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CotacaoResponse", description = "Resultado do cálculo da cotação")
public record CotacaoResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "2.00") BigDecimal premio,
    @Schema(example = "0.10") BigDecimal corretagem,
    @Schema(example = "2.10") BigDecimal total,
    @Schema(example = "0.18") BigDecimal valorParcela
) {}

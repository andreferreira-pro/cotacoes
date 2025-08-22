package com.seguros.cotacoes.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CotacaoRequest(
    @NotNull @DecimalMin("0.01") BigDecimal valorEmprestimo,
    @NotNull @DecimalMin("0.000000") BigDecimal taxaPremio,      // ex: 0.0002
    @NotNull @DecimalMin("0.000000") BigDecimal taxaCorretagem   // ex: 0.05
) {}

package com.seguros.cotacoes.api.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Schema(name = "CotacaoRequest", description = "Dados de entrada para criação de cotação")
public record CotacaoRequest(
    @NotNull @DecimalMin("0.01")
    @Schema(example = "10000.00", description = "Valor do empréstimo")
    BigDecimal valorEmprestimo,

    @NotNull @DecimalMin("0.000000")
    @Schema(example = "0.0002", description = "Taxa de prêmio sugerida (decimal). Ex.: 0.0002 = 0,02%")
    BigDecimal taxaPremio,

    @NotNull @DecimalMin("0.000000")
    @Schema(example = "0.05", description = "Taxa de corretagem (decimal). Ex.: 0.05 = 5%")
    BigDecimal taxaCorretagem
) {}

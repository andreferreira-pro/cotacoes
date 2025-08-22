package com.seguros.cotacoes.api.dto;

import java.math.BigDecimal;

public record CotacaoResponse(
    Long id,
    BigDecimal premio,
    BigDecimal corretagem,
    BigDecimal total,
    BigDecimal valorParcela
) {}

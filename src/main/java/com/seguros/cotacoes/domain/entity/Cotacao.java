package com.seguros.cotacoes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Armazena os dados de entrada (valor do empréstimo, taxas, prazo) -
 * Armazena os resultados do cálculo (prêmio, corretagem, total e parcela)
 */

@Entity
@Table(name = "cotacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cotacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// ----- Entradas -----
	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal valorEmprestimo; // Ex.: 10000.00

	@Column(nullable = false, precision = 10, scale = 6)
	private BigDecimal taxaPremio; // Ex.: 0.0002 (0,02%)

	@Column(nullable = false, precision = 10, scale = 6)
	private BigDecimal taxaCorretagem; // Ex.: 0.05 (5%)

	@Column(nullable = false)
	private Integer prazoMeses; // No MVP: 12

	// ----- Resultados do cálculo -----
	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal premio; // valorEmprestimo * taxaPremio

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal corretagem; // premio * taxaCorretagem

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal total; // premio + corretagem

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal valorParcela; // total / prazoMeses (2 casas, HALF_UP)

	// ----- Auditoria simples -----
	@Column(nullable = false)
	private OffsetDateTime criadoEm; // preenchido na Service
}

package com.seguros.cotacoes.domain.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cotacao")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal valorEmprestimo;

    @Column(nullable=false, precision=10, scale=6)
    private BigDecimal taxaPremio;

    @Column(nullable=false, precision=10, scale=6)
    private BigDecimal taxaCorretagem;

    @Column(nullable=false)
    private Integer prazoMeses;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal premio;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal corretagem;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal total;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal valorParcela;

    @Column(nullable=false)
    private OffsetDateTime criadoEm;

    public Cotacao() {}

    // Getters e Setters
    public Long getId() { return id; }
    public BigDecimal getValorEmprestimo() { return valorEmprestimo; }
    public BigDecimal getTaxaPremio() { return taxaPremio; }
    public BigDecimal getTaxaCorretagem() { return taxaCorretagem; }
    public Integer getPrazoMeses() { return prazoMeses; }
    public BigDecimal getPremio() { return premio; }
    public BigDecimal getCorretagem() { return corretagem; }
    public BigDecimal getTotal() { return total; }
    public BigDecimal getValorParcela() { return valorParcela; }
    public OffsetDateTime getCriadoEm() { return criadoEm; }

    public void setId(Long id) { this.id = id; }
    public void setValorEmprestimo(BigDecimal valorEmprestimo) { this.valorEmprestimo = valorEmprestimo; }
    public void setTaxaPremio(BigDecimal taxaPremio) { this.taxaPremio = taxaPremio; }
    public void setTaxaCorretagem(BigDecimal taxaCorretagem) { this.taxaCorretagem = taxaCorretagem; }
    public void setPrazoMeses(Integer prazoMeses) { this.prazoMeses = prazoMeses; }
    public void setPremio(BigDecimal premio) { this.premio = premio; }
    public void setCorretagem(BigDecimal corretagem) { this.corretagem = corretagem; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setValorParcela(BigDecimal valorParcela) { this.valorParcela = valorParcela; }
    public void setCriadoEm(OffsetDateTime criadoEm) { this.criadoEm = criadoEm; }
}

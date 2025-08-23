package com.seguros.cotacoes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguros.cotacoes.domain.entity.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {}

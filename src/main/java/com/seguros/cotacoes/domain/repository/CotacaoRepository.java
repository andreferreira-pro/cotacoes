package com.seguros.cotacoes.domain.repository;

import com.seguros.cotacoes.domain.entity.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {}

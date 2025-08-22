package com.seguros.cotacoes.domain.service;

import com.seguros.cotacoes.api.dto.CotacaoRequest;
import com.seguros.cotacoes.api.dto.CotacaoResponse;
import com.seguros.cotacoes.domain.entity.Cotacao;
import com.seguros.cotacoes.domain.repository.CotacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Service
public class CotacaoService {

    private final CotacaoRepository repository;

    // Construtor explícito (injeta o repository)
    public CotacaoService(CotacaoRepository repository) {
        this.repository = repository;
    }

    public CotacaoResponse criar(CotacaoRequest req) {
        final int prazo = 12;

        BigDecimal premio = req.valorEmprestimo().multiply(req.taxaPremio());
        BigDecimal corretagem = premio.multiply(req.taxaCorretagem());
        BigDecimal total = premio.add(corretagem);
        BigDecimal parcela = total.divide(BigDecimal.valueOf(prazo), 2, RoundingMode.HALF_UP);

        Cotacao c = new Cotacao();
        c.setValorEmprestimo(req.valorEmprestimo());
        c.setTaxaPremio(req.taxaPremio());
        c.setTaxaCorretagem(req.taxaCorretagem());
        c.setPrazoMeses(prazo);
        c.setPremio(premio.setScale(2, RoundingMode.HALF_UP));
        c.setCorretagem(corretagem.setScale(2, RoundingMode.HALF_UP));
        c.setTotal(total.setScale(2, RoundingMode.HALF_UP));
        c.setValorParcela(parcela);
        c.setCriadoEm(OffsetDateTime.now());

        c = repository.save(c);
        return new CotacaoResponse(c.getId(), c.getPremio(), c.getCorretagem(), c.getTotal(), c.getValorParcela());
    }

    public CotacaoResponse buscar(Long id) {
        Cotacao c = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cotação não encontrada"));
        return new CotacaoResponse(c.getId(), c.getPremio(), c.getCorretagem(), c.getTotal(), c.getValorParcela());
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Cotação não encontrada");
        }
        repository.deleteById(id);
    }
}

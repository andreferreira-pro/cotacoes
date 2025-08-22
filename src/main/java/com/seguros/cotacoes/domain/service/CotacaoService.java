package com.seguros.cotacoes.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List; // << necessário

import org.springframework.stereotype.Service;

import com.seguros.cotacoes.api.dto.CotacaoRequest;
import com.seguros.cotacoes.api.dto.CotacaoResponse;
import com.seguros.cotacoes.domain.entity.Cotacao;
import com.seguros.cotacoes.domain.repository.CotacaoRepository;

@Service
public class CotacaoService {

    private final CotacaoRepository repository;

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
        return toResponse(c);
    }

    public CotacaoResponse buscar(Long id) {
        Cotacao c = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cotação não encontrada"));
        return toResponse(c);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Cotação não encontrada");
        }
        repository.deleteById(id);
    }

    // >>>>>>> ADICIONE ESSES DOIS MÉTODOS <<<<<<<

    public List<CotacaoResponse> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::toResponse) // precisa do método abaixo com essa assinatura exata
                .toList();
    }

    private CotacaoResponse toResponse(Cotacao c) {
        return new CotacaoResponse(
                c.getId(),
                c.getPremio(),
                c.getCorretagem(),
                c.getTotal(),
                c.getValorParcela()
        );
    }
}

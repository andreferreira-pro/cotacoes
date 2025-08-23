package com.seguros.cotacoes.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.seguros.cotacoes.api.dto.CotacaoRequest;
import com.seguros.cotacoes.domain.entity.Cotacao;
import com.seguros.cotacoes.domain.repository.CotacaoRepository;

class CotacaoServiceTest {

    @Test
    void deveCalcularSalvarERetornarValoresEsperados() {
        // Mock do repository
        CotacaoRepository repo = mock(CotacaoRepository.class);
        when(repo.save(any(Cotacao.class))).thenAnswer(inv -> {
            Cotacao c = inv.getArgument(0);
            c.setId(1L); // simula ID gerado pelo banco
            return c;
        });

        // Service sob teste
        CotacaoService service = new CotacaoService(repo);

        // Entrada
        var req = new CotacaoRequest(
                new BigDecimal("10000.00"),
                new BigDecimal("0.0002"),
                new BigDecimal("0.05")
        );

        // Execução
        var resp = service.criar(req);

        // Verificações dos cálculos e do ID
        assertEquals("2.00", resp.premio().toPlainString());       // 10000 * 0.0002
        assertEquals("0.10", resp.corretagem().toPlainString());   // 2.00 * 0.05
        assertEquals("2.10", resp.total().toPlainString());        // 2.00 + 0.10
        assertEquals("0.18", resp.valorParcela().toPlainString()); // 2.10 / 12 => 0.18
        assertEquals(1L, resp.id());

        // Garante que salvou uma vez
        verify(repo, times(1)).save(any(Cotacao.class));
    }
}

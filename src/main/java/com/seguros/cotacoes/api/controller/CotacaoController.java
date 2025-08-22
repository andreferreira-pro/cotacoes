package com.seguros.cotacoes.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seguros.cotacoes.api.dto.CotacaoRequest;
import com.seguros.cotacoes.api.dto.CotacaoResponse;
import com.seguros.cotacoes.domain.service.CotacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    private final CotacaoService service;

    // Sem Lombok: construtor explícito para injeção de dependência
    public CotacaoController(CotacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CotacaoResponse criar(@RequestBody @Valid CotacaoRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<CotacaoResponse> listar() {
        return service.listarTodas();
    }
    
    @GetMapping("/{id}")
    public CotacaoResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}

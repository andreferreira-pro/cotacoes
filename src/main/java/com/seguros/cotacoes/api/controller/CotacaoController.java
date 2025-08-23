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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cotações", description = "CRUD e cálculos de cotação de seguro prestamista")
@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    private final CotacaoService service;

    public CotacaoController(CotacaoService service) {
        this.service = service;
    }

    @Operation(
        summary = "Criar cotação",
        description = "Calcula e persiste uma nova cotação com base no valor do empréstimo e nas taxas.",
        security = @SecurityRequirement(name = "bearerAuth"),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CotacaoRequest.class),
                examples = @ExampleObject(
                    name = "Exemplo de entrada",
                    value = "{\n  \"valorEmprestimo\": 10000.00,\n  \"taxaPremio\": 0.0002,\n  \"taxaCorretagem\": 0.05\n}"
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Criado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CotacaoResponse.class),
                    examples = @ExampleObject(
                        name = "Exemplo de saída",
                        value = "{\n  \"id\": 1,\n  \"premio\": 2.00,\n  \"corretagem\": 0.10,\n  \"total\": 2.10,\n  \"valorParcela\": 0.18\n}"
                    )
                )
            ),
            @ApiResponse(responseCode = "400", description = "Erro de validação", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
        }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CotacaoResponse criar(@RequestBody @Valid CotacaoRequest request) {
        return service.criar(request);
    }

    @Operation(
        summary = "Buscar cotação por ID",
        description = "Retorna a cotação correspondente ao ID informado.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = CotacaoResponse.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrada", content = @Content)
        }
    )
    @GetMapping("/{id}")
    public CotacaoResponse buscar(
            @Parameter(description = "ID da cotação", example = "1")
            @PathVariable Long id) {
        return service.buscar(id);
    }

    @Operation(
        summary = "Listar todas as cotações",
        description = "Retorna a lista de cotações cadastradas.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = CotacaoResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
        }
    )
    @GetMapping
    public List<CotacaoResponse> listar() {
        return service.listarTodas();
    }

    @Operation(
        summary = "Excluir cotação por ID",
        description = "Remove a cotação correspondente ao ID informado.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "204", description = "Excluída", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrada", content = @Content)
        }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @Parameter(description = "ID da cotação", example = "1")
            @PathVariable Long id) {
        service.excluir(id);
    }
}

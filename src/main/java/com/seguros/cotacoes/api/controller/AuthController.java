package com.seguros.cotacoes.api.controller;

import com.seguros.cotacoes.api.dto.LoginRequest;
import com.seguros.cotacoes.api.dto.TokenResponse;
import com.seguros.cotacoes.infra.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticação", description = "Endpoints de autenticação e emissão de token JWT")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final String cfgUser;
    private final String cfgPass;

    public AuthController(
            JwtService jwtService,
            @Value("${app.security.auth.username}") String cfgUser,
            @Value("${app.security.auth.password}") String cfgPass) {
        this.jwtService = jwtService;
        this.cfgUser = cfgUser;
        this.cfgPass = cfgPass;
    }

    @Operation(
        summary = "Login",
        description = "Valida credenciais e gera um token JWT para acessar os endpoints protegidos.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = LoginRequest.class),
                examples = @ExampleObject(
                    name = "Exemplo de login",
                    value = "{ \"username\": \"admin\", \"password\": \"admin\" }"
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TokenResponse.class),
                    examples = @ExampleObject(
                        name = "Token de exemplo",
                        value = "{ \"token\": \"Bearer eyJhbGciOiJIUzI1NiJ9...\" }"
                    )
                )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content)
        }
    )
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest req) {
        if (req == null || req.username() == null || req.password() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (cfgUser.equals(req.username()) && cfgPass.equals(req.password())) {
            String token = jwtService.gerarToken(req.username());
            return ResponseEntity.ok(new TokenResponse("Bearer " + token));
        }
        return ResponseEntity.status(401).build();
    }
}

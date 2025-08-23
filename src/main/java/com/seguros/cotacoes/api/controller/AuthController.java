package com.seguros.cotacoes.api.controller;

import com.seguros.cotacoes.api.dto.LoginRequest;
import com.seguros.cotacoes.api.dto.TokenResponse;
import com.seguros.cotacoes.infra.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest req) {
        if (req == null || req.username() == null || req.password() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (cfgUser.equals(req.username()) && cfgPass.equals(req.password())) {
            String token = jwtService.gerarToken(req.username());
            return ResponseEntity.ok(new TokenResponse(token));
        }
        return ResponseEntity.status(401).build();
    }
}

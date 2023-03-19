package com.ws.taskmanager.controller;

import com.ws.taskmanager.data.DTO.JWTAuthResponseDto;
import com.ws.taskmanager.data.DTO.LoginDto;
import com.ws.taskmanager.data.DTO.RegisterDto;
import com.ws.taskmanager.data.DTO.RegisterResponseDto;
import com.ws.taskmanager.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerDto));
    }
}

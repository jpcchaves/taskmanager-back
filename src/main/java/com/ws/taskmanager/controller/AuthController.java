package com.ws.taskmanager.controller;

import com.ws.taskmanager.data.DTO.*;
import com.ws.taskmanager.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateUserResponseDto> update(@RequestBody UpdateUserRequestDto updateUserDto,
                                                        @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.update(updateUserDto, id));
    }
}

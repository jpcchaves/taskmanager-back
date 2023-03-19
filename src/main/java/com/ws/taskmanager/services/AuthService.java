package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.JWTAuthResponseDto;
import com.ws.taskmanager.data.DTO.LoginDto;
import com.ws.taskmanager.data.DTO.RegisterDto;

public interface AuthService {
    JWTAuthResponseDto login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}

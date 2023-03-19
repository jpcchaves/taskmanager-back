package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.JWTAuthResponseDto;
import com.ws.taskmanager.data.DTO.LoginDto;
import com.ws.taskmanager.data.DTO.RegisterDto;
import com.ws.taskmanager.data.DTO.RegisterResponseDto;

public interface AuthService {
    JWTAuthResponseDto login(LoginDto loginDto);

    RegisterResponseDto register(RegisterDto registerDto);
}

package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.*;

public interface AuthService {
    JWTAuthResponseDto login(LoginDto loginDto);

    RegisterResponseDto register(RegisterDto registerDto);

    UpdateUserResponseDto update(UpdateUserRequestDto updateUserDto, Long id);
}

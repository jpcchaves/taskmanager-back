package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.LoginDto;
import com.ws.taskmanager.data.DTO.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}

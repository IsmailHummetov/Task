package com.example.authservice.service.Inter;

import com.example.authservice.dto.LoginDto;
import com.example.authservice.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto register);

    String login(LoginDto login);
}

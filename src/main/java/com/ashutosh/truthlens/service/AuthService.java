package com.ashutosh.truthlens.service;

import com.ashutosh.truthlens.dto.AuthResponse;
import com.ashutosh.truthlens.dto.LoginRequest;
import com.ashutosh.truthlens.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}

package com.ashutosh.interviewer.service;

import com.ashutosh.interviewer.dto.AuthResponse;
import com.ashutosh.interviewer.dto.LoginRequest;
import com.ashutosh.interviewer.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}

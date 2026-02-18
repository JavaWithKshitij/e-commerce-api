package com.example.authservice.service;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.LoginRequest;

public interface CustomerService {

    String register(AuthRequest request);

    AuthResponse login(LoginRequest loginRequest);

}

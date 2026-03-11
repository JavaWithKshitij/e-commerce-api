package com.example.authservice.service;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.entity.User;

import java.util.Optional;

public interface UserService {

    String register(AuthRequest request);

    String login(LoginRequest loginRequest);

    Optional<User> findByUsername(String username);

}

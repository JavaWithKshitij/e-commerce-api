package com.example.authservice.service.impl;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.entity.Customer;
import com.example.authservice.enums.Role;
import com.example.authservice.respository.CustomerRepository;
import com.example.authservice.service.CustomerService;
import com.example.authservice.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(AuthRequest request) {
        // 1. Check if user already exists
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken!");
        }

        // 2. Map DTO to Entity & Encode Password
        Customer customer = Customer.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.CUSTOMER)
                .build();

        // 3. Save to H2 Database
        repository.save(customer);
        return "Customer registered successfully!";
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // 1. Authenticate using Spring Security's AuthenticationManager
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (authenticate.isAuthenticated()) {
            // 2. Generate JWT Token
            String token = JwtUtil.generateToken(loginRequest.getUsername());

            // 3. Return DTO Response
            return AuthResponse.builder()
                    .token(token)
                    .username(loginRequest.getUsername())
                    .role(Role.CUSTOMER)
                    .message("Login Successful")
                    .build();
        } else {
            throw new RuntimeException("Invalid access - check credentials");
        }
    }
}
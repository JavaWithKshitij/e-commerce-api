package com.example.authservice.service.impl;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.entity.User;
import com.example.authservice.enums.Role;
import com.example.authservice.respository.UserRepository;
import com.example.authservice.service.UserService;
import com.example.authservice.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(AuthRequest request) {
        // 1. Check if user already exists
        if (findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken!");
        }

        // 2. Map DTO to Entity & Encode Password
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        // 3. Save to H2 Database
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User dbUser = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();

        if (passwordEncoder.matches(loginRequest.getPassword(), dbUser.getPassword())) {
            return JwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole().name());
        }
        throw new RuntimeException("Invalid Credentials");
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
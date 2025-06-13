package com.dailyfarm.authservice.controller;

import com.dailyfarm.authservice.dto.AuthResponse;
import com.dailyfarm.authservice.dto.LoginRequest;
import com.dailyfarm.authservice.dto.RegisterRequest;
import com.dailyfarm.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email already exists");
        }
        userService.register(request);
        return new AuthResponse("Registration successful");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        if (userService.authenticate(request)) {
            return new AuthResponse("Login successful");
        }
        return new AuthResponse("Invalid credentials");
    }
}

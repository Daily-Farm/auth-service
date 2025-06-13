package com.dailyfarm.authservice.service;

import com.dailyfarm.authservice.dto.RegisterRequest;
import com.dailyfarm.authservice.dto.LoginRequest;
import com.dailyfarm.authservice.entity.User;
import com.dailyfarm.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    public boolean authenticate(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
            .map(user -> user.getPassword().equals(request.getPassword()))
            .orElse(false);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}

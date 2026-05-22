package com.group3.SIS.modules.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group3.SIS.common.exception.NoSuchCustomerExistsException;
import com.group3.SIS.modules.auth.dto.AuthResponseDto;
import com.group3.SIS.modules.auth.dto.LoginDto;
import com.group3.SIS.modules.auth.dto.RegisterDto;
import com.group3.SIS.modules.auth.security.JwtProvider;
import com.group3.SIS.modules.users.entity.Role;
import com.group3.SIS.modules.users.entity.usersEntity;
import com.group3.SIS.modules.users.repository.usersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final usersRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDto login(LoginDto loginDto) {
        usersEntity user = userRepository.findByUsername(loginDto.getUsername())
            .orElseThrow(() -> new NoSuchCustomerExistsException("User not found"));

        if (Boolean.FALSE.equals(user.getIsActive())) {
            throw new IllegalArgumentException("User account is disabled");
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        String token = jwtProvider.generateToken(user);
        return new AuthResponseDto(token, "Bearer", user.getId(), user.getUsername(), 
                                   user.getEmail(), user.getRole().name());
    }

    public AuthResponseDto register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        usersEntity user = new usersEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setRole(parseRole(registerDto.getRole()));
        user.setIsActive(true);

        userRepository.save(user);

        String token = jwtProvider.generateToken(user);
        return new AuthResponseDto(token, "Bearer", user.getId(), user.getUsername(), 
                                   user.getEmail(), user.getRole().name());
    }

    public usersEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new NoSuchCustomerExistsException("User not found"));
    }

    private Role parseRole(String role) {
        if (role == null || role.isBlank()) {
            return Role.ROLE_STUDENT;
        }

        String normalizedRole = role.trim().toUpperCase();
        if (!normalizedRole.startsWith("ROLE_")) {
            normalizedRole = "ROLE_" + normalizedRole;
        }

        try {
            return Role.valueOf(normalizedRole);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}

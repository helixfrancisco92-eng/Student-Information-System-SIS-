package com.group3.SIS.modules.auth.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group3.SIS.modules.auth.dto.AuthResponseDto;
import com.group3.SIS.modules.auth.dto.LoginDto;
import com.group3.SIS.modules.auth.dto.RegisterDto;
import com.group3.SIS.modules.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public Map<String, String> loginInfo() {
        return Map.of(
            "message", "Send a POST request to /auth/login with username and password."
        );
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @GetMapping("/register")
    public Map<String, String> registerInfo() {
        return Map.of(
            "message", "Send a POST request to /auth/register with username, password, email, firstName, lastName, and role."
        );
    }

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }
}

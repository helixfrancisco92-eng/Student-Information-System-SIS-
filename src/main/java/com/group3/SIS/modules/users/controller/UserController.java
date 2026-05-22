package com.group3.SIS.modules.users.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group3.SIS.modules.users.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @DeleteMapping("/{id}")
    public void DeleteUser(Long id) {
        userService.DeleteUser(id);
    }
}

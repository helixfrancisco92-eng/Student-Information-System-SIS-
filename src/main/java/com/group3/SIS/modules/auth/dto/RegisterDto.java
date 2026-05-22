package com.group3.SIS.modules.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role; // ROLE_ADMIN, ROLE_STUDENT, ROLE_FACULTY
}

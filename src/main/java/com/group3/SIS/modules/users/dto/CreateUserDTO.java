package com.group3.SIS.modules.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    private Long id;
    private String username;
    private String password;    
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}

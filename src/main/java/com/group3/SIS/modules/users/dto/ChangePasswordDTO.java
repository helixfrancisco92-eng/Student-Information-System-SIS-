package com.group3.SIS.modules.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {
    private Long userId;
    private String oldPassword;
    private String newPassword;
    
}

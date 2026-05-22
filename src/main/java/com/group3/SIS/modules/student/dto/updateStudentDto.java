package com.group3.SIS.modules.student.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class updateStudentDto {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotBlank private String middleName;
    @NotBlank private String studentNumber;
    @NotBlank private String course;

}

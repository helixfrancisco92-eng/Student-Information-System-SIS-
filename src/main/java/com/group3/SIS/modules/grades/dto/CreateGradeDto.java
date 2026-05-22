package com.group3.SIS.modules.grades.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGradeDto {
    private Long enrollmentId;
    private Double grade;
    private String remarks;
}

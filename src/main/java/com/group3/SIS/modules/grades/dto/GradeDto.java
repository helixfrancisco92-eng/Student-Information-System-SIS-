package com.group3.SIS.modules.grades.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeDto {
    private Long id;
    private Long enrollmentId;
    private Double grade;
    private String remarks;
}

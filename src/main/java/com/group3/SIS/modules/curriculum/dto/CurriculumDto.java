package com.group3.SIS.modules.curriculum.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurriculumDto {
    private Long id;
    private String code;
    private String name;
    private String semester;
    private String course;
    private String academicYear;
}

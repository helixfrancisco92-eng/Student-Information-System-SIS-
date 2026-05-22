package com.group3.SIS.modules.faculty.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String FacultyNumber;
    private String Course;
}

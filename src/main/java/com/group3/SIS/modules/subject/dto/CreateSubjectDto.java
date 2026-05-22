package com.group3.SIS.modules.subject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubjectDto {
    private String code;
    private String name;
    private Integer units;
}

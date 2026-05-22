package com.group3.SIS.modules.curriculum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "curriculum")

public class CurriculumEntity {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id    
@Column
private Long id;

@Column
private String code;
@Column
private String name;
@Column
private String academicYear;
@Column
private String course;
@Column
private String semester;

}

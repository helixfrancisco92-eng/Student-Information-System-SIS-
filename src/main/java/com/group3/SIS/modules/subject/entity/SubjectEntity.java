package com.group3.SIS.modules.subject.entity;

import java.util.ArrayList;
import java.util.List;

import com.group3.SIS.modules.enrollment.entity.EnrollmentEntity;
import com.group3.SIS.modules.schedule.entity.ScheduleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "subjects")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column 
    private String code;

    @Column
    private String name;

    @Column
    private Integer units;


    @OneToMany(mappedBy = "subjects")
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "subjects")
    private List<ScheduleEntity> schedules = new ArrayList<>();
    
}


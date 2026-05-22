package com.group3.SIS.modules.enrollment.entity;
import com.group3.SIS.modules.grades.entity.GradeEntity;
import com.group3.SIS.modules.student.entity.StudentEntity;
import com.group3.SIS.modules.subject.entity.SubjectEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enrollments")
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentEntity students;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubjectEntity subjects;

    @OneToOne(mappedBy = "enrollments")
    private GradeEntity grades; 
}

package com.group3.SIS.modules.student.entity;
import java.util.ArrayList;
import java.util.List;
import com.group3.SIS.modules.users.entity.usersEntity;
import com.group3.SIS.modules.enrollment.entity.EnrollmentEntity;
import com.group3.SIS.modules.section.entity.SectionEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentEntity {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String studentNumber;

    @Column
    private String FirstName;

    @Column
    private String MiddleName;
    
    @Column
    private String LastName;
    
    @Column
    private String Course;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="section_id")
    private SectionEntity section;

    @OneToMany(mappedBy="students")
    private List<EnrollmentEntity> enrollments = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private usersEntity user;
}

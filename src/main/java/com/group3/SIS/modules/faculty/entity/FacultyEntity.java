package com.group3.SIS.modules.faculty.entity;
import com.group3.SIS.modules.users.entity.usersEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "faculties")

public class FacultyEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String FacultyNumber;

    @Column
    private String FirstName;

    @Column
    private String MiddleName;
    
    @Column
    private String LastName;
    
    @Column
    private String Course;

    @OneToOne
    @JoinColumn(name = "user_id")
    private usersEntity user;

}

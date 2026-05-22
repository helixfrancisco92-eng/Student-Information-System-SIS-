package com.group3.SIS.modules.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.SIS.modules.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    
}

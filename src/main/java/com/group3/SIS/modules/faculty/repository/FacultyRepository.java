package com.group3.SIS.modules.faculty.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.SIS.modules.faculty.entity.FacultyEntity;

public interface  FacultyRepository extends JpaRepository<FacultyEntity, Long> {

}

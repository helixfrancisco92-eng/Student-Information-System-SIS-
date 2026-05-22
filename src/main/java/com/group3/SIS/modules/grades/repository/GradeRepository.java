package com.group3.SIS.modules.grades.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group3.SIS.modules.grades.entity.GradeEntity;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {

}

package com.group3.SIS.modules.enrollment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group3.SIS.modules.enrollment.entity.EnrollmentEntity;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

}

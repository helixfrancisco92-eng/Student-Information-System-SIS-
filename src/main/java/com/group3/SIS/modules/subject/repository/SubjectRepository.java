package com.group3.SIS.modules.subject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group3.SIS.modules.subject.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

}

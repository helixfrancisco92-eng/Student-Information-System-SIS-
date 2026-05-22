package com.group3.SIS.modules.curriculum.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.SIS.modules.curriculum.entity.CurriculumEntity;

public interface CurriculumRepository extends JpaRepository<CurriculumEntity, Long> {

}

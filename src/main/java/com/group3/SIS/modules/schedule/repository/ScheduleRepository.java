package com.group3.SIS.modules.schedule.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group3.SIS.modules.schedule.entity.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

}

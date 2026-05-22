package com.group3.SIS.modules.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
    private Long id;
    private String day;
    private String startTime;
    private String endTime;
    private String room;
    private Long subjectId;
    private Long sectionId;
}

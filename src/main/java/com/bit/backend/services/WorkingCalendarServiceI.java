package com.bit.backend.services;

import com.bit.backend.dtos.WorkingCalendarDto;

import java.util.List;

public interface WorkingCalendarServiceI {
    WorkingCalendarDto addWorkingCalendar(WorkingCalendarDto studentDto);
    List<WorkingCalendarDto> getAllWorkingCalendars();
    WorkingCalendarDto getWorkingCalendarById(long id);
    WorkingCalendarDto updateWorkingCalendar(long id, WorkingCalendarDto workingCalendarDto);
    WorkingCalendarDto deleteWorkingCalendar(long id);
}

package com.bit.backend.services.impl;

import com.bit.backend.dtos.WorkingCalendarDto;
import com.bit.backend.entities.WorkingCalendarEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.WorkingCalendarMapper;
import com.bit.backend.repositories.WorkingCalendarRepository;
import com.bit.backend.services.WorkingCalendarServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkingCalendarServiceImpl implements WorkingCalendarServiceI {

    private final WorkingCalendarRepository workingCalendarRepository;
    private final WorkingCalendarMapper workingCalendarMapper;

    public WorkingCalendarServiceImpl(WorkingCalendarRepository workingCalendarRepository,
                              WorkingCalendarMapper workingCalendarMapper) {
        this.workingCalendarRepository = workingCalendarRepository;
        this.workingCalendarMapper = workingCalendarMapper;
    }

    @Override
    @Transactional
    public WorkingCalendarDto addWorkingCalendar(WorkingCalendarDto workingCalendarDto) {
        WorkingCalendarEntity entity = workingCalendarMapper.toWorkingCalendarEntity(workingCalendarDto);
        entity.setId(null);

        WorkingCalendarEntity saved = workingCalendarRepository.save(entity);

        return workingCalendarMapper.toWorkingCalendarDto(saved);
    }

    @Override
    public List<WorkingCalendarDto> getAllWorkingCalendars() {
        return workingCalendarMapper.toWorkingCalendarDtoList(workingCalendarRepository.findAll());
    }

    @Override
    public WorkingCalendarDto getWorkingCalendarById(long id) {
        WorkingCalendarEntity entity = workingCalendarRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkingCalendar not found", HttpStatus.NOT_FOUND));
        return workingCalendarMapper.toWorkingCalendarDto(entity);
    }

    @Override
    @Transactional
    public WorkingCalendarDto updateWorkingCalendar(long id, WorkingCalendarDto workingCalendarDto) {
        WorkingCalendarEntity existing = workingCalendarRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkingCalendar not found", HttpStatus.NOT_FOUND));

        existing.setDayOfWeek(workingCalendarDto.getDayOfWeek());
        existing.setIsWorkingDay(workingCalendarDto.getIsWorkingDay());

        return workingCalendarMapper.toWorkingCalendarDto(workingCalendarRepository.save(existing));
    }

    @Override
    @Transactional
    public WorkingCalendarDto deleteWorkingCalendar(long id) {
        WorkingCalendarEntity existing = workingCalendarRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkingCalendar not found", HttpStatus.NOT_FOUND));
        WorkingCalendarDto dto = workingCalendarMapper.toWorkingCalendarDto(existing);
        workingCalendarRepository.delete(existing);
        return dto;
    }

}

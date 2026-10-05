package com.bit.backend.services.impl;

import com.bit.backend.dtos.HolidayDto;
import com.bit.backend.entities.HolidayEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.HolidayMapper;
import com.bit.backend.repositories.HolidayRepository;
import com.bit.backend.services.HolidayServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayServiceI {

    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;

    public HolidayServiceImpl(HolidayRepository holidayRepository,
                              HolidayMapper holidayMapper) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
    }

    @Override
    @Transactional
    public HolidayDto addHoliday(HolidayDto holidayDto) {
        HolidayEntity entity = holidayMapper.toHolidayEntity(holidayDto);
        entity.setId(null);

        HolidayEntity saved = holidayRepository.save(entity);

        return holidayMapper.toHolidayDto(saved);
    }

    @Override
    public List<HolidayDto> getAllHolidays() {
        return holidayMapper.toHolidayDtoList(holidayRepository.findAll());
    }

    @Override
    public HolidayDto getHolidayById(long id) {
        HolidayEntity entity = holidayRepository.findById(id)
                .orElseThrow(() -> new AppException("Holiday not found", HttpStatus.NOT_FOUND));
        return holidayMapper.toHolidayDto(entity);
    }

    @Override
    @Transactional
    public HolidayDto updateHoliday(long id, HolidayDto holidayDto) {
        HolidayEntity existing = holidayRepository.findById(id)
                .orElseThrow(() -> new AppException("Holiday not found", HttpStatus.NOT_FOUND));

        existing.setHolidayDate(holidayDto.getHolidayDate());
        existing.setHolidayName(holidayDto.getHolidayName());
        existing.setIsPaid(holidayDto.getIsPaid());

        return holidayMapper.toHolidayDto(holidayRepository.save(existing));
    }

    @Override
    @Transactional
    public HolidayDto deleteHoliday(long id) {
        HolidayEntity existing = holidayRepository.findById(id)
                .orElseThrow(() -> new AppException("Holiday not found", HttpStatus.NOT_FOUND));
        HolidayDto dto = holidayMapper.toHolidayDto(existing);
        holidayRepository.delete(existing);
        return dto;
    }
}

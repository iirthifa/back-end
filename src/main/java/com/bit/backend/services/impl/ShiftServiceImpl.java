package com.bit.backend.services.impl;

import com.bit.backend.dtos.ShiftDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.ShiftEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ShiftMapper;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.ShiftRepository;
import com.bit.backend.services.ShiftServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftServiceI {

    private final ShiftRepository shiftRepository;
    private final StatusRepository statusRepository;
    private final ShiftMapper shiftMapper;

    public ShiftServiceImpl(ShiftRepository shiftRepository,
                              StatusRepository statusRepository,
                              ShiftMapper shiftMapper) {
        this.shiftRepository = shiftRepository;
        this.statusRepository = statusRepository;
        this.shiftMapper = shiftMapper;
    }

    @Override
    @Transactional
    public ShiftDto addShift(ShiftDto shiftDto) {
        StatusEntity status = resolveStatus(shiftDto);
        ShiftEntity entity = shiftMapper.toShiftEntity(shiftDto);
        entity.setId(null);
        entity.setStatus(status);

        ShiftEntity saved = shiftRepository.save(entity);
        if (saved.getShiftCode() == null || saved.getShiftCode().isBlank()) {
            saved.setShiftCode("STU-" + saved.getId());
            saved = shiftRepository.save(saved);
        }
        return shiftMapper.toShiftDto(saved);
    }

    @Override
    public List<ShiftDto> getAllShifts() {
        return shiftMapper.toShiftDtoList(shiftRepository.findAll());
    }

    @Override
    public ShiftDto getShiftById(long id) {
        ShiftEntity entity = shiftRepository.findById(id)
                .orElseThrow(() -> new AppException("Shift not found", HttpStatus.NOT_FOUND));
        return shiftMapper.toShiftDto(entity);
    }

    @Override
    @Transactional
    public ShiftDto updateShift(long id, ShiftDto shiftDto) {
        ShiftEntity existing = shiftRepository.findById(id)
                .orElseThrow(() -> new AppException("Shift not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(shiftDto);
        existing.setShiftName(shiftDto.getShiftName());
        existing.setStartTime(shiftDto.getStartTime());
        existing.setEndTime(shiftDto.getEndTime());
        existing.setBreakMinutes(shiftDto.getBreakMinutes());
        existing.setOtMultiplier(shiftDto.getOtMultiplier());
        existing.setStatus(status);
        if (shiftDto.getShiftCode() != null && !shiftDto.getShiftCode().isBlank()) {
            existing.setShiftCode(shiftDto.getShiftCode());
        }

        return shiftMapper.toShiftDto(shiftRepository.save(existing));
    }

    @Override
    @Transactional
    public ShiftDto deleteShift(long id) {
        ShiftEntity existing = shiftRepository.findById(id)
                .orElseThrow(() -> new AppException("Shift not found", HttpStatus.NOT_FOUND));
        ShiftDto dto = shiftMapper.toShiftDto(existing);
        shiftRepository.delete(existing);
        return dto;
    }

    private StatusEntity resolveStatus(ShiftDto shiftDto) {
        if (shiftDto.getStatus() == null || shiftDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(shiftDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}

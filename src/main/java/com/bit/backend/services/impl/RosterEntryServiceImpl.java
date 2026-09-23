package com.bit.backend.services.impl;

import com.bit.backend.dtos.RosterEntryDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.ShiftEntity;
import com.bit.backend.entities.RosterPeriodEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.RosterEntryEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.RosterEntryMapper;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.repositories.ShiftRepository;
import com.bit.backend.repositories.RosterPeriodRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.RosterEntryRepository;
import com.bit.backend.services.RosterEntryServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RosterEntryServiceImpl implements RosterEntryServiceI {

    private final RosterEntryRepository rosterEntryRepository;
    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;
    private final RosterPeriodRepository rosterPeriodRepository;
    private final StatusRepository statusRepository;
    private final RosterEntryMapper rosterEntryMapper;

    public RosterEntryServiceImpl(RosterEntryRepository rosterEntryRepository,
                                  EmployeeRepository employeeRepository,
                                  ShiftRepository shiftRepository,
                                  RosterPeriodRepository rosterPeriodRepository,
                                  StatusRepository statusRepository,
                                  RosterEntryMapper rosterEntryMapper) {
        this.rosterEntryRepository = rosterEntryRepository;
        this.employeeRepository = employeeRepository;
        this.shiftRepository = shiftRepository;
        this.rosterPeriodRepository = rosterPeriodRepository;
        this.statusRepository = statusRepository;
        this.rosterEntryMapper = rosterEntryMapper;
    }

    @Override
    @Transactional
    public RosterEntryDto addRosterEntry(RosterEntryDto rosterEntryDto) {
        EmployeeEntity employee = resolveEmployee(rosterEntryDto);
        ShiftEntity shift = resolveShift(rosterEntryDto);
        RosterPeriodEntity rosterPeriod = resolveRosterPeriod(rosterEntryDto);
        StatusEntity status = resolveStatus(rosterEntryDto);

        RosterEntryEntity entity = rosterEntryMapper.toRosterEntryEntity(rosterEntryDto);
        entity.setId(null);
        entity.setEmployee(employee);
        entity.setShift(shift);
        entity.setRosterPeriod(rosterPeriod);
        entity.setStatus(status);

        RosterEntryEntity saved = rosterEntryRepository.save(entity);
        /*if (saved.getRosterEntryCode() == null || saved.getRosterEntryCode().isBlank()) {
            saved.setRosterEntryCode("STU-" + saved.getId());
            saved = rosterEntryRepository.save(saved);
        }*/
        return rosterEntryMapper.toRosterEntryDto(saved);
    }

    @Override
    public List<RosterEntryDto> getAllRosterEntries() {
        return rosterEntryMapper.toRosterEntryDtoList(rosterEntryRepository.findAll());
    }

    @Override
    public RosterEntryDto getRosterEntryById(long id) {
        RosterEntryEntity entity = rosterEntryRepository.findById(id)
                .orElseThrow(() -> new AppException("RosterEntry not found", HttpStatus.NOT_FOUND));
        return rosterEntryMapper.toRosterEntryDto(entity);
    }

    @Override
    @Transactional
    public RosterEntryDto updateRosterEntry(long id, RosterEntryDto rosterEntryDto) {
        RosterEntryEntity existing = rosterEntryRepository.findById(id)
                .orElseThrow(() -> new AppException("RosterEntry not found", HttpStatus.NOT_FOUND));

        EmployeeEntity employee = resolveEmployee(rosterEntryDto);
        ShiftEntity shift = resolveShift(rosterEntryDto);
        RosterPeriodEntity rosterPeriod = resolveRosterPeriod(rosterEntryDto);
        StatusEntity status = resolveStatus(rosterEntryDto);
        existing.setWorkDate(rosterEntryDto.getWorkDate());
        existing.setNotes(rosterEntryDto.getNotes());
        existing.setEmployee(employee);
        existing.setShift(shift);
        existing.setRosterPeriod(rosterPeriod);
        existing.setStatus(status);
        /*if (rosterEntryDto.getRosterEntryCode() != null && !rosterEntryDto.getRosterEntryCode().isBlank()) {
            existing.setRosterEntryCode(rosterEntryDto.getRosterEntryCode());
        }*/

        return rosterEntryMapper.toRosterEntryDto(rosterEntryRepository.save(existing));
    }

    @Override
    @Transactional
    public RosterEntryDto deleteRosterEntry(long id) {
        RosterEntryEntity existing = rosterEntryRepository.findById(id)
                .orElseThrow(() -> new AppException("RosterEntry not found", HttpStatus.NOT_FOUND));
        RosterEntryDto dto = rosterEntryMapper.toRosterEntryDto(existing);
        rosterEntryRepository.delete(existing);
        return dto;
    }

    private EmployeeEntity resolveEmployee(RosterEntryDto rosterEntryDto) {
        if (rosterEntryDto.getEmployee() == null || rosterEntryDto.getEmployee().getId() == null) {
            throw new AppException("Employee is required", HttpStatus.BAD_REQUEST);
        }
        return employeeRepository.findById(rosterEntryDto.getEmployee().getId())
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.BAD_REQUEST));
    }

    private ShiftEntity resolveShift(RosterEntryDto rosterEntryDto) {
        if (rosterEntryDto.getShift() == null || rosterEntryDto.getShift().getId() == null) {
            throw new AppException("Shift is required", HttpStatus.BAD_REQUEST);
        }
        return shiftRepository.findById(rosterEntryDto.getShift().getId())
                .orElseThrow(() -> new AppException("Shift not found", HttpStatus.BAD_REQUEST));
    }

    private RosterPeriodEntity resolveRosterPeriod(RosterEntryDto rosterEntryDto) {
        if (rosterEntryDto.getRosterPeriod() == null || rosterEntryDto.getRosterPeriod().getId() == null) {
            throw new AppException("RosterPeriod is required", HttpStatus.BAD_REQUEST);
        }
        return rosterPeriodRepository.findById(rosterEntryDto.getRosterPeriod().getId())
                .orElseThrow(() -> new AppException("RosterPeriod not found", HttpStatus.BAD_REQUEST));
    }

    private StatusEntity resolveStatus(RosterEntryDto rosterEntryDto) {
        if (rosterEntryDto.getStatus() == null || rosterEntryDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(rosterEntryDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}

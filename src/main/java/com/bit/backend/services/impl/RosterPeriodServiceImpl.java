package com.bit.backend.services.impl;

import com.bit.backend.dtos.RosterPeriodDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.RosterPeriodEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.RosterPeriodMapper;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.RosterPeriodRepository;
import com.bit.backend.services.RosterPeriodServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RosterPeriodServiceImpl implements RosterPeriodServiceI {

    private final RosterPeriodRepository rosterPeriodRepository;
    private final StatusRepository statusRepository;
    private final RosterPeriodMapper rosterPeriodMapper;

    public RosterPeriodServiceImpl(RosterPeriodRepository rosterPeriodRepository,
                              StatusRepository statusRepository,
                              RosterPeriodMapper rosterPeriodMapper) {
        this.rosterPeriodRepository = rosterPeriodRepository;
        this.statusRepository = statusRepository;
        this.rosterPeriodMapper = rosterPeriodMapper;
    }

    @Override
    @Transactional
    public RosterPeriodDto addRosterPeriod(RosterPeriodDto rosterPeriodDto) {
        StatusEntity status = resolveStatus(rosterPeriodDto);
        RosterPeriodEntity entity = rosterPeriodMapper.toRosterPeriodEntity(rosterPeriodDto);
        entity.setId(null);
        entity.setStatus(status);

        RosterPeriodEntity saved = rosterPeriodRepository.save(entity);
        /*if (saved.getRosterPeriodCode() == null || saved.getRosterPeriodCode().isBlank()) {
            saved.setRosterPeriodCode("STU-" + saved.getId());
            saved = rosterPeriodRepository.save(saved);
        }*/
        return rosterPeriodMapper.toRosterPeriodDto(saved);
    }

    @Override
    public List<RosterPeriodDto> getAllRosterPeriods() {
        return rosterPeriodMapper.toRosterPeriodDtoList(rosterPeriodRepository.findAll());
    }

    @Override
    public RosterPeriodDto getRosterPeriodById(long id) {
        RosterPeriodEntity entity = rosterPeriodRepository.findById(id)
                .orElseThrow(() -> new AppException("RosterPeriod not found", HttpStatus.NOT_FOUND));
        return rosterPeriodMapper.toRosterPeriodDto(entity);
    }

    @Override
    @Transactional
    public RosterPeriodDto updateRosterPeriod(long id, RosterPeriodDto rosterPeriodDto) {
        RosterPeriodEntity existing = rosterPeriodRepository.findById(id)
                .orElseThrow(() -> new AppException("RosterPeriod not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(rosterPeriodDto);
        existing.setPeriodName(rosterPeriodDto.getPeriodName());
        existing.setStartDate(rosterPeriodDto.getStartDate());
        existing.setEndDate(rosterPeriodDto.getEndDate());
        existing.setPublished(rosterPeriodDto.getPublished());
        existing.setStatus(status);
        /*if (rosterPeriodDto.getRosterPeriodCode() != null && !rosterPeriodDto.getRosterPeriodCode().isBlank()) {
            existing.setRosterPeriodCode(rosterPeriodDto.getRosterPeriodCode());
        }*/

        return rosterPeriodMapper.toRosterPeriodDto(rosterPeriodRepository.save(existing));
    }

    @Override
    @Transactional
    public RosterPeriodDto deleteRosterPeriod(long id) {
        RosterPeriodEntity existing = rosterPeriodRepository.findById(id)
                .orElseThrow(() -> new AppException("RosterPeriod not found", HttpStatus.NOT_FOUND));
        RosterPeriodDto dto = rosterPeriodMapper.toRosterPeriodDto(existing);
        rosterPeriodRepository.delete(existing);
        return dto;
    }

    private StatusEntity resolveStatus(RosterPeriodDto rosterPeriodDto) {
        if (rosterPeriodDto.getStatus() == null || rosterPeriodDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(rosterPeriodDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}

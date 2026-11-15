package com.bit.backend.services.impl;

import com.bit.backend.dtos.PoliceStationDto;
import com.bit.backend.entities.PoliceStationEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.PoliceStationMapper;
import com.bit.backend.repositories.PoliceStationRepository;
import com.bit.backend.services.PoliceStationServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class PoliceStationServiceImpl implements PoliceStationServiceI {

    private final PoliceStationRepository policeStationRepository;
    private final PoliceStationMapper policeStationMapper;

    public PoliceStationServiceImpl(PoliceStationRepository policeStationRepository, PoliceStationMapper policeStationMapper) {
        this.policeStationRepository = policeStationRepository;
        this.policeStationMapper = policeStationMapper;
    }

    @Override
    @Transactional
    public PoliceStationDto addPoliceStation(PoliceStationDto policeStationDto) {
        PoliceStationEntity entity = policeStationMapper.toPoliceStationEntity(policeStationDto);

        entity.setPolice_station_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return policeStationMapper.toPoliceStationDto(policeStationRepository.save(entity));
    }

    @Override
    public List<PoliceStationDto> getAllPoliceStations() {
        return policeStationMapper.toPoliceStationDtoList(policeStationRepository.findAll());
    }

    @Override
    public PoliceStationDto getPoliceStationById(long id) {
        PoliceStationEntity entity = policeStationRepository.findById(id)
                .orElseThrow(() -> new AppException("PoliceStation not found", HttpStatus.NOT_FOUND));
        return policeStationMapper.toPoliceStationDto(entity);
    }

    @Override
    @Transactional
    public PoliceStationDto updatePoliceStation(long id, PoliceStationDto policeStationDto) {
        PoliceStationEntity existing = policeStationRepository.findById(id)
                .orElseThrow(() -> new AppException("PoliceStation not found", HttpStatus.NOT_FOUND));

        setIfPresent(policeStationDto.getPoliceStationName(), existing::setPoliceStationName);
        setIfPresent(policeStationDto.getPoliceDivision(), existing::setPoliceDivision);
        setIfPresent(policeStationDto.getPhone(), existing::setPhone);
        setIfPresent(policeStationDto.getProvinceId(), existing::setProvinceId);
        setIfPresent(policeStationDto.getActive(), existing::setActive);

        return policeStationMapper.toPoliceStationDto(policeStationRepository.save(existing));
    }

    @Override
    @Transactional
    public PoliceStationDto deletePoliceStation(long id) {
        PoliceStationEntity existing = policeStationRepository.findById(id)
                .orElseThrow(() -> new AppException("PoliceStation not found", HttpStatus.NOT_FOUND));
        PoliceStationDto dto = policeStationMapper.toPoliceStationDto(existing);
        policeStationRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

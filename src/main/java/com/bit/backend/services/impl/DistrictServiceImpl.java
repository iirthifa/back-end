package com.bit.backend.services.impl;

import com.bit.backend.dtos.DistrictDto;
import com.bit.backend.entities.DistrictEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DistrictMapper;
import com.bit.backend.repositories.DistrictRepository;
import com.bit.backend.services.DistrictServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class DistrictServiceImpl implements DistrictServiceI {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    @Override
    @Transactional
    public DistrictDto addDistrict(DistrictDto districtDto) {
        DistrictEntity entity = districtMapper.toDistrictEntity(districtDto);

        entity.setDistrict_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return districtMapper.toDistrictDto(districtRepository.save(entity));
    }

    @Override
    public List<DistrictDto> getAllDistricts() {
        return districtMapper.toDistrictDtoList(districtRepository.findAll());
    }

    @Override
    public DistrictDto getDistrictById(long id) {
        DistrictEntity entity = districtRepository.findById(id)
                .orElseThrow(() -> new AppException("District not found", HttpStatus.NOT_FOUND));
        return districtMapper.toDistrictDto(entity);
    }

    @Override
    @Transactional
    public DistrictDto updateDistrict(long id, DistrictDto districtDto) {
        DistrictEntity existing = districtRepository.findById(id)
                .orElseThrow(() -> new AppException("District not found", HttpStatus.NOT_FOUND));

        setIfPresent(districtDto.getDistrictName(), existing::setDistrictName);
        setIfPresent(districtDto.getProvinceSerialId(), existing::setProvinceSerialId);
        setIfPresent(districtDto.getActive(), existing::setActive);

        return districtMapper.toDistrictDto(districtRepository.save(existing));
    }

    @Override
    @Transactional
    public DistrictDto deleteDistrict(long id) {
        DistrictEntity existing = districtRepository.findById(id)
                .orElseThrow(() -> new AppException("District not found", HttpStatus.NOT_FOUND));
        DistrictDto dto = districtMapper.toDistrictDto(existing);
        districtRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

package com.bit.backend.services.impl;

import com.bit.backend.dtos.ProvinceDto;
import com.bit.backend.entities.ProvinceEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ProvinceMapper;
import com.bit.backend.repositories.ProvinceRepository;
import com.bit.backend.services.ProvinceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ProvinceServiceImpl implements ProvinceServiceI {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
    }

    @Override
    @Transactional
    public ProvinceDto addProvince(ProvinceDto provinceDto) {
        ProvinceEntity entity = provinceMapper.toProvinceEntity(provinceDto);

        entity.setProvince_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return provinceMapper.toProvinceDto(provinceRepository.save(entity));
    }

    @Override
    public List<ProvinceDto> getAllProvinces() {
        return provinceMapper.toProvinceDtoList(provinceRepository.findAll());
    }

    @Override
    public ProvinceDto getProvinceById(long id) {
        ProvinceEntity entity = provinceRepository.findById(id)
                .orElseThrow(() -> new AppException("Province not found", HttpStatus.NOT_FOUND));
        return provinceMapper.toProvinceDto(entity);
    }

    @Override
    @Transactional
    public ProvinceDto updateProvince(long id, ProvinceDto provinceDto) {
        ProvinceEntity existing = provinceRepository.findById(id)
                .orElseThrow(() -> new AppException("Province not found", HttpStatus.NOT_FOUND));

        setIfPresent(provinceDto.getProvinceName(), existing::setProvinceName);
        setIfPresent(provinceDto.getActive(), existing::setActive);

        return provinceMapper.toProvinceDto(provinceRepository.save(existing));
    }

    @Override
    @Transactional
    public ProvinceDto deleteProvince(long id) {
        ProvinceEntity existing = provinceRepository.findById(id)
                .orElseThrow(() -> new AppException("Province not found", HttpStatus.NOT_FOUND));
        ProvinceDto dto = provinceMapper.toProvinceDto(existing);
        provinceRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

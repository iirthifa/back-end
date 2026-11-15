package com.bit.backend.services.impl;

import com.bit.backend.dtos.NationalityDto;
import com.bit.backend.entities.NationalityEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.NationalityMapper;
import com.bit.backend.repositories.NationalityRepository;
import com.bit.backend.services.NationalityServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class NationalityServiceImpl implements NationalityServiceI {

    private final NationalityRepository nationalityRepository;
    private final NationalityMapper nationalityMapper;

    public NationalityServiceImpl(NationalityRepository nationalityRepository, NationalityMapper nationalityMapper) {
        this.nationalityRepository = nationalityRepository;
        this.nationalityMapper = nationalityMapper;
    }

    @Override
    @Transactional
    public NationalityDto addNationality(NationalityDto nationalityDto) {
        NationalityEntity entity = nationalityMapper.toNationalityEntity(nationalityDto);

        entity.setNationality_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return nationalityMapper.toNationalityDto(nationalityRepository.save(entity));
    }

    @Override
    public List<NationalityDto> getAllNationalitys() {
        return nationalityMapper.toNationalityDtoList(nationalityRepository.findAll());
    }

    @Override
    public NationalityDto getNationalityById(long id) {
        NationalityEntity entity = nationalityRepository.findById(id)
                .orElseThrow(() -> new AppException("Nationality not found", HttpStatus.NOT_FOUND));
        return nationalityMapper.toNationalityDto(entity);
    }

    @Override
    @Transactional
    public NationalityDto updateNationality(long id, NationalityDto nationalityDto) {
        NationalityEntity existing = nationalityRepository.findById(id)
                .orElseThrow(() -> new AppException("Nationality not found", HttpStatus.NOT_FOUND));

        setIfPresent(nationalityDto.getNationalityName(), existing::setNationalityName);
        setIfPresent(nationalityDto.getActive(), existing::setActive);

        return nationalityMapper.toNationalityDto(nationalityRepository.save(existing));
    }

    @Override
    @Transactional
    public NationalityDto deleteNationality(long id) {
        NationalityEntity existing = nationalityRepository.findById(id)
                .orElseThrow(() -> new AppException("Nationality not found", HttpStatus.NOT_FOUND));
        NationalityDto dto = nationalityMapper.toNationalityDto(existing);
        nationalityRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

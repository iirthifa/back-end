package com.bit.backend.services.impl;

import com.bit.backend.dtos.DivisionalSecretariatDto;
import com.bit.backend.entities.DivisionalSecretariatEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DivisionalSecretariatMapper;
import com.bit.backend.repositories.DivisionalSecretariatRepository;
import com.bit.backend.services.DivisionalSecretariatServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class DivisionalSecretariatServiceImpl implements DivisionalSecretariatServiceI {

    private final DivisionalSecretariatRepository divisionalSecretariatRepository;
    private final DivisionalSecretariatMapper divisionalSecretariatMapper;

    public DivisionalSecretariatServiceImpl(DivisionalSecretariatRepository divisionalSecretariatRepository, DivisionalSecretariatMapper divisionalSecretariatMapper) {
        this.divisionalSecretariatRepository = divisionalSecretariatRepository;
        this.divisionalSecretariatMapper = divisionalSecretariatMapper;
    }

    @Override
    @Transactional
    public DivisionalSecretariatDto addDivisionalSecretariat(DivisionalSecretariatDto divisionalSecretariatDto) {
        DivisionalSecretariatEntity entity = divisionalSecretariatMapper.toDivisionalSecretariatEntity(divisionalSecretariatDto);

        entity.setDev_sect_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return divisionalSecretariatMapper.toDivisionalSecretariatDto(divisionalSecretariatRepository.save(entity));
    }

    @Override
    public List<DivisionalSecretariatDto> getAllDivisionalSecretariats() {
        return divisionalSecretariatMapper.toDivisionalSecretariatDtoList(divisionalSecretariatRepository.findAll());
    }

    @Override
    public DivisionalSecretariatDto getDivisionalSecretariatById(long id) {
        DivisionalSecretariatEntity entity = divisionalSecretariatRepository.findById(id)
                .orElseThrow(() -> new AppException("DivisionalSecretariat not found", HttpStatus.NOT_FOUND));
        return divisionalSecretariatMapper.toDivisionalSecretariatDto(entity);
    }

    @Override
    @Transactional
    public DivisionalSecretariatDto updateDivisionalSecretariat(long id, DivisionalSecretariatDto divisionalSecretariatDto) {
        DivisionalSecretariatEntity existing = divisionalSecretariatRepository.findById(id)
                .orElseThrow(() -> new AppException("DivisionalSecretariat not found", HttpStatus.NOT_FOUND));

        setIfPresent(divisionalSecretariatDto.getDivisionalSecretariatName(), existing::setDivisionalSecretariatName);
        setIfPresent(divisionalSecretariatDto.getDistrictSerialId(), existing::setDistrictSerialId);
        setIfPresent(divisionalSecretariatDto.getActive(), existing::setActive);

        return divisionalSecretariatMapper.toDivisionalSecretariatDto(divisionalSecretariatRepository.save(existing));
    }

    @Override
    @Transactional
    public DivisionalSecretariatDto deleteDivisionalSecretariat(long id) {
        DivisionalSecretariatEntity existing = divisionalSecretariatRepository.findById(id)
                .orElseThrow(() -> new AppException("DivisionalSecretariat not found", HttpStatus.NOT_FOUND));
        DivisionalSecretariatDto dto = divisionalSecretariatMapper.toDivisionalSecretariatDto(existing);
        divisionalSecretariatRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

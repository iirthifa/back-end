package com.bit.backend.services.impl;

import com.bit.backend.dtos.GnDivisionDto;
import com.bit.backend.entities.GnDivisionEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.GnDivisionMapper;
import com.bit.backend.repositories.GnDivisionRepository;
import com.bit.backend.services.GnDivisionServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class GnDivisionServiceImpl implements GnDivisionServiceI {

    private final GnDivisionRepository gnDivisionRepository;
    private final GnDivisionMapper gnDivisionMapper;

    public GnDivisionServiceImpl(GnDivisionRepository gnDivisionRepository, GnDivisionMapper gnDivisionMapper) {
        this.gnDivisionRepository = gnDivisionRepository;
        this.gnDivisionMapper = gnDivisionMapper;
    }

    @Override
    @Transactional
    public GnDivisionDto addGnDivision(GnDivisionDto gnDivisionDto) {
        GnDivisionEntity entity = gnDivisionMapper.toGnDivisionEntity(gnDivisionDto);

        entity.setGnd_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return gnDivisionMapper.toGnDivisionDto(gnDivisionRepository.save(entity));
    }

    @Override
    public List<GnDivisionDto> getAllGnDivisions() {
        return gnDivisionMapper.toGnDivisionDtoList(gnDivisionRepository.findAll());
    }

    @Override
    public GnDivisionDto getGnDivisionById(long id) {
        GnDivisionEntity entity = gnDivisionRepository.findById(id)
                .orElseThrow(() -> new AppException("GnDivision not found", HttpStatus.NOT_FOUND));
        return gnDivisionMapper.toGnDivisionDto(entity);
    }

    @Override
    @Transactional
    public GnDivisionDto updateGnDivision(long id, GnDivisionDto gnDivisionDto) {
        GnDivisionEntity existing = gnDivisionRepository.findById(id)
                .orElseThrow(() -> new AppException("GnDivision not found", HttpStatus.NOT_FOUND));

        setIfPresent(gnDivisionDto.getGnDivision(), existing::setGnDivision);
        setIfPresent(gnDivisionDto.getGnCode(), existing::setGnCode);
        setIfPresent(gnDivisionDto.getMpaCode(), existing::setMpaCode);
        setIfPresent(gnDivisionDto.getDivSectSerialId(), existing::setDivSectSerialId);
        setIfPresent(gnDivisionDto.getActive(), existing::setActive);

        return gnDivisionMapper.toGnDivisionDto(gnDivisionRepository.save(existing));
    }

    @Override
    @Transactional
    public GnDivisionDto deleteGnDivision(long id) {
        GnDivisionEntity existing = gnDivisionRepository.findById(id)
                .orElseThrow(() -> new AppException("GnDivision not found", HttpStatus.NOT_FOUND));
        GnDivisionDto dto = gnDivisionMapper.toGnDivisionDto(existing);
        gnDivisionRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

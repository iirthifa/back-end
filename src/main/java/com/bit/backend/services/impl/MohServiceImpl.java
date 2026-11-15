package com.bit.backend.services.impl;

import com.bit.backend.dtos.MohDto;
import com.bit.backend.entities.MohEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MohMapper;
import com.bit.backend.repositories.MohRepository;
import com.bit.backend.services.MohServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class MohServiceImpl implements MohServiceI {

    private final MohRepository mohRepository;
    private final MohMapper mohMapper;

    public MohServiceImpl(MohRepository mohRepository, MohMapper mohMapper) {
        this.mohRepository = mohRepository;
        this.mohMapper = mohMapper;
    }

    @Override
    @Transactional
    public MohDto addMoh(MohDto mohDto) {
        MohEntity entity = mohMapper.toMohEntity(mohDto);

        entity.setMoh_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return mohMapper.toMohDto(mohRepository.save(entity));
    }

    @Override
    public List<MohDto> getAllMohs() {
        return mohMapper.toMohDtoList(mohRepository.findAll());
    }

    @Override
    public MohDto getMohById(long id) {
        MohEntity entity = mohRepository.findById(id)
                .orElseThrow(() -> new AppException("Moh not found", HttpStatus.NOT_FOUND));
        return mohMapper.toMohDto(entity);
    }

    @Override
    @Transactional
    public MohDto updateMoh(long id, MohDto mohDto) {
        MohEntity existing = mohRepository.findById(id)
                .orElseThrow(() -> new AppException("Moh not found", HttpStatus.NOT_FOUND));

        setIfPresent(mohDto.getMohName(), existing::setMohName);
        setIfPresent(mohDto.getContactNumber(), existing::setContactNumber);
        setIfPresent(mohDto.getDistrictSerialId(), existing::setDistrictSerialId);
        setIfPresent(mohDto.getActive(), existing::setActive);

        return mohMapper.toMohDto(mohRepository.save(existing));
    }

    @Override
    @Transactional
    public MohDto deleteMoh(long id) {
        MohEntity existing = mohRepository.findById(id)
                .orElseThrow(() -> new AppException("Moh not found", HttpStatus.NOT_FOUND));
        MohDto dto = mohMapper.toMohDto(existing);
        mohRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

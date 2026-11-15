package com.bit.backend.services.impl;

import com.bit.backend.dtos.SectionDto;
import com.bit.backend.entities.SectionEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SectionMapper;
import com.bit.backend.repositories.SectionRepository;
import com.bit.backend.services.SectionServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class SectionServiceImpl implements SectionServiceI {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    public SectionServiceImpl(SectionRepository sectionRepository, SectionMapper sectionMapper) {
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
    }

    @Override
    @Transactional
    public SectionDto addSection(SectionDto sectionDto) {
        SectionEntity entity = sectionMapper.toSectionEntity(sectionDto);

        entity.setSect_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return sectionMapper.toSectionDto(sectionRepository.save(entity));
    }

    @Override
    public List<SectionDto> getAllSections() {
        return sectionMapper.toSectionDtoList(sectionRepository.findAll());
    }

    @Override
    public SectionDto getSectionById(long id) {
        SectionEntity entity = sectionRepository.findById(id)
                .orElseThrow(() -> new AppException("Section not found", HttpStatus.NOT_FOUND));
        return sectionMapper.toSectionDto(entity);
    }

    @Override
    @Transactional
    public SectionDto updateSection(long id, SectionDto sectionDto) {
        SectionEntity existing = sectionRepository.findById(id)
                .orElseThrow(() -> new AppException("Section not found", HttpStatus.NOT_FOUND));

        setIfPresent(sectionDto.getSectName(), existing::setSectName);
        setIfPresent(sectionDto.getDeptSerialId(), existing::setDeptSerialId);
        setIfPresent(sectionDto.getActive(), existing::setActive);

        return sectionMapper.toSectionDto(sectionRepository.save(existing));
    }

    @Override
    @Transactional
    public SectionDto deleteSection(long id) {
        SectionEntity existing = sectionRepository.findById(id)
                .orElseThrow(() -> new AppException("Section not found", HttpStatus.NOT_FOUND));
        SectionDto dto = sectionMapper.toSectionDto(existing);
        sectionRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

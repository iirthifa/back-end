package com.bit.backend.services.impl;

import com.bit.backend.dtos.DesignationDto;
import com.bit.backend.entities.DesignationEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DesignationMapper;
import com.bit.backend.repositories.DesignationRepository;
import com.bit.backend.services.DesignationServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DesignationServiceImpl implements DesignationServiceI {

    private final DesignationRepository designationRepository;
    private final DesignationMapper designationMapper;

    public DesignationServiceImpl(DesignationRepository designationRepository,
                              DesignationMapper designationMapper) {
        this.designationRepository = designationRepository;
        this.designationMapper = designationMapper;
    }

    @Override
    @Transactional
    public DesignationDto addDesignation(DesignationDto designationDto) {
        DesignationEntity entity = designationMapper.toDesignationEntity(designationDto);
        entity.setId(null);

        DesignationEntity saved = designationRepository.save(entity);
        return designationMapper.toDesignationDto(saved);
    }

    @Override
    public List<DesignationDto> getAllDesignations() {
        return designationMapper.toDesignationDtoList(designationRepository.findAll());
    }

    @Override
    public DesignationDto getDesignationById(long id) {
        DesignationEntity entity = designationRepository.findById(id)
                .orElseThrow(() -> new AppException("Designation not found", HttpStatus.NOT_FOUND));
        return designationMapper.toDesignationDto(entity);
    }

    @Override
    @Transactional
    public DesignationDto updateDesignation(long id, DesignationDto designationDto) {
        DesignationEntity existing = designationRepository.findById(id)
                .orElseThrow(() -> new AppException("Designation not found", HttpStatus.NOT_FOUND));

        existing.setDesignationName(designationDto.getDesignationName());
        existing.setGradeLevel(designationDto.getGradeLevel());
        existing.setDescription(designationDto.getDescription());

        return designationMapper.toDesignationDto(designationRepository.save(existing));
    }

    @Override
    @Transactional
    public DesignationDto deleteDesignation(long id) {
        DesignationEntity existing = designationRepository.findById(id)
                .orElseThrow(() -> new AppException("Designation not found", HttpStatus.NOT_FOUND));
        DesignationDto dto = designationMapper.toDesignationDto(existing);
        designationRepository.delete(existing);
        return dto;
    }
}

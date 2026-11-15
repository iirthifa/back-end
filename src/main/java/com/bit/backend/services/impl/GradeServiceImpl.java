package com.bit.backend.services.impl;

import com.bit.backend.dtos.GradeDto;
import com.bit.backend.entities.GradeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.GradeMapper;
import com.bit.backend.repositories.GradeRepository;
import com.bit.backend.services.GradeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class GradeServiceImpl implements GradeServiceI {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    @Transactional
    public GradeDto addGrade(GradeDto gradeDto) {
        GradeEntity entity = gradeMapper.toGradeEntity(gradeDto);

        entity.setGrade_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return gradeMapper.toGradeDto(gradeRepository.save(entity));
    }

    @Override
    public List<GradeDto> getAllGrades() {
        return gradeMapper.toGradeDtoList(gradeRepository.findAll());
    }

    @Override
    public GradeDto getGradeById(long id) {
        GradeEntity entity = gradeRepository.findById(id)
                .orElseThrow(() -> new AppException("Grade not found", HttpStatus.NOT_FOUND));
        return gradeMapper.toGradeDto(entity);
    }

    @Override
    @Transactional
    public GradeDto updateGrade(long id, GradeDto gradeDto) {
        GradeEntity existing = gradeRepository.findById(id)
                .orElseThrow(() -> new AppException("Grade not found", HttpStatus.NOT_FOUND));

        setIfPresent(gradeDto.getGradeName(), existing::setGradeName);
        setIfPresent(gradeDto.getActive(), existing::setActive);

        return gradeMapper.toGradeDto(gradeRepository.save(existing));
    }

    @Override
    @Transactional
    public GradeDto deleteGrade(long id) {
        GradeEntity existing = gradeRepository.findById(id)
                .orElseThrow(() -> new AppException("Grade not found", HttpStatus.NOT_FOUND));
        GradeDto dto = gradeMapper.toGradeDto(existing);
        gradeRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

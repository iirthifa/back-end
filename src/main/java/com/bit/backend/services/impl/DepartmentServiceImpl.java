package com.bit.backend.services.impl;

import com.bit.backend.dtos.DepartmentDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.DepartmentEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DepartmentMapper;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.DepartmentRepository;
import com.bit.backend.services.DepartmentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentServiceI {

    private final DepartmentRepository departmentRepository;
    private final StatusRepository statusRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                              StatusRepository statusRepository,
                              DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.statusRepository = statusRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @Transactional
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        StatusEntity status = resolveStatus(departmentDto);
        DepartmentEntity entity = departmentMapper.toDepartmentEntity(departmentDto);
        entity.setId(null);
        entity.setStatus(status);

        DepartmentEntity saved = departmentRepository.save(entity);
        if (saved.getDeptCode() == null || saved.getDeptCode().isBlank()) {
            saved.setDeptCode("STU-" + saved.getId());
            saved = departmentRepository.save(saved);
        }
        return departmentMapper.toDepartmentDto(saved);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentMapper.toDepartmentDtoList(departmentRepository.findAll());
    }

    @Override
    public DepartmentDto getDepartmentById(long id) {
        DepartmentEntity entity = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException("Department not found", HttpStatus.NOT_FOUND));
        return departmentMapper.toDepartmentDto(entity);
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(long id, DepartmentDto departmentDto) {
        DepartmentEntity existing = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException("Department not found", HttpStatus.NOT_FOUND));

        StatusEntity status = resolveStatus(departmentDto);
        existing.setDeptName(departmentDto.getDeptName());
        existing.setDescription(departmentDto.getDescription());
        //existing.setHeadEmployee(headEmployee);
        existing.setStatus(status);
        if (departmentDto.getDeptCode() != null && !departmentDto.getDeptCode().isBlank()) {
            existing.setDeptCode(departmentDto.getDeptCode());
        }

        return departmentMapper.toDepartmentDto(departmentRepository.save(existing));
    }

    @Override
    @Transactional
    public DepartmentDto deleteDepartment(long id) {
        DepartmentEntity existing = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException("Department not found", HttpStatus.NOT_FOUND));
        DepartmentDto dto = departmentMapper.toDepartmentDto(existing);
        departmentRepository.delete(existing);
        return dto;
    }

    private StatusEntity resolveStatus(DepartmentDto departmentDto) {
        if (departmentDto.getStatus() == null || departmentDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(departmentDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}

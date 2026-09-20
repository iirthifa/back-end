package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
//import com.bit.backend.entities.AppUserEntity;
import com.bit.backend.entities.DepartmentEntity;
import com.bit.backend.entities.DesignationEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EmployeeMapper;
//import com.bit.backend.repositories.AppUserRepository;
import com.bit.backend.repositories.DepartmentRepository;
import com.bit.backend.repositories.DesignationRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;
    //private final AppUserRepository appUserRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final StatusRepository statusRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               //AppUserRepository appUserRepository,
                               DepartmentRepository departmentRepository,
                               DesignationRepository designationRepository,
                               StatusRepository statusRepository,
                               EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        //this.appUserRepository = appUserRepository;
        this.departmentRepository = departmentRepository;
        this.designationRepository = designationRepository;
        this.statusRepository = statusRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        //AppUserEntity user = resolveUser(employeeDto);
        DepartmentEntity department = resolveDepartment(employeeDto);
        DesignationEntity designation = resolveDesignation(employeeDto);
        StatusEntity status = resolveStatus(employeeDto);
        EmployeeEntity entity = employeeMapper.toEmployeeEntity(employeeDto);
        entity.setId(null);
        //entity.setUser(user);
        entity.setDepartment(department);
        entity.setDesignation(designation);
        entity.setStatus(status);

        EmployeeEntity saved = employeeRepository.save(entity);
        if (saved.getEmployeeCode() == null || saved.getEmployeeCode().isBlank()) {
            saved.setEmployeeCode("EMP-" + saved.getId());
            saved = employeeRepository.save(saved);
        }
        return employeeMapper.toEmployeeDto(saved);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toEmployeeDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));
        return employeeMapper.toEmployeeDto(entity);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        EmployeeEntity existing = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));

        //AppUserEntity user = resolveUser(employeeDto);
        DepartmentEntity department = resolveDepartment(employeeDto);
        DesignationEntity designation = resolveDesignation(employeeDto);
        StatusEntity status = resolveStatus(employeeDto);
        existing.setFirstName(employeeDto.getFirstName());
        existing.setLastName(employeeDto.getLastName());
        existing.setEmail(employeeDto.getEmail());
        existing.setPhone(employeeDto.getPhone());
        existing.setNic(employeeDto.getNic());
        existing.setGender(employeeDto.getGender());
        existing.setAddress(employeeDto.getAddress());
        existing.setDateOfBirth(employeeDto.getDateOfBirth());
        existing.setHireDate(employeeDto.getHireDate());
        existing.setResignDate(employeeDto.getResignDate());
        existing.setBankName(employeeDto.getBankName());
        existing.setBankAccount(employeeDto.getBankAccount());
        //existing.setUser(user);
        existing.setDepartment(department);
        existing.setDesignation(designation);
        existing.setStatus(status);
        if (employeeDto.getEmployeeCode() != null && !employeeDto.getEmployeeCode().isBlank()) {
            existing.setEmployeeCode(employeeDto.getEmployeeCode());
        }

        return employeeMapper.toEmployeeDto(employeeRepository.save(existing));
    }

    @Override
    @Transactional
    public EmployeeDto deleteEmployee(long id) {
        EmployeeEntity existing = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));
        EmployeeDto dto = employeeMapper.toEmployeeDto(existing);
        employeeRepository.delete(existing);
        return dto;
    }

    /*private AppUserEntity resolveUser(EmployeeDto employeeDto) {
        if (employeeDto.getUser() == null || employeeDto.getUser().getId() == null) {
            throw new AppException("User is required", HttpStatus.BAD_REQUEST);
        }
        return appUserRepository.findById(employeeDto.getUser().getId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.BAD_REQUEST));
    }*/

    private DepartmentEntity resolveDepartment(EmployeeDto employeeDto) {
        if (employeeDto.getDepartment() == null || employeeDto.getDepartment().getId() == null) {
            throw new AppException("Department is required", HttpStatus.BAD_REQUEST);
        }
        return departmentRepository.findById(employeeDto.getDepartment().getId())
                .orElseThrow(() -> new AppException("Department not found", HttpStatus.BAD_REQUEST));
    }

    private DesignationEntity resolveDesignation(EmployeeDto employeeDto) {
        if (employeeDto.getDesignation() == null || employeeDto.getDesignation().getId() == null) {
            throw new AppException("Designation is required", HttpStatus.BAD_REQUEST);
        }
        return designationRepository.findById(employeeDto.getDesignation().getId())
                .orElseThrow(() -> new AppException("Designation not found", HttpStatus.BAD_REQUEST));
    }

    private StatusEntity resolveStatus(EmployeeDto employeeDto) {
        if (employeeDto.getStatus() == null || employeeDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(employeeDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}

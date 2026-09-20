package com.bit.backend.services;

import com.bit.backend.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentServiceI {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentById(long id);
    DepartmentDto updateDepartment(long id, DepartmentDto departmentDto);
    DepartmentDto deleteDepartment(long id);
}

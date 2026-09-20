package com.bit.backend.services;

import com.bit.backend.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeServiceI {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(long id);
    EmployeeDto updateEmployee(long id, EmployeeDto employeeDto);
    EmployeeDto deleteEmployee(long id);
}

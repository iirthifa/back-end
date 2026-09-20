package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeServiceI employeeServiceI;
    private final StatusServiceI statusServiceI;

    public EmployeeController(EmployeeServiceI employeeServiceI, StatusServiceI statusServiceI) {
        this.employeeServiceI = employeeServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("employee/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/employee")
    public ResponseEntity<ApiListResponse<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(ApiListResponse.of(employeeServiceI.getAllEmployees()));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiListResponse<EmployeeDto>> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(employeeServiceI.getEmployeeById(id)));
    }

    @PostMapping("/employee")
    public ResponseEntity<ApiListResponse<EmployeeDto>> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto created = employeeServiceI.addEmployee(employeeDto);
        return ResponseEntity.created(URI.create("/api/v1/employee/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<ApiListResponse<EmployeeDto>> updateEmployee(
            @PathVariable long id,
            @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(employeeServiceI.updateEmployee(id, employeeDto)));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ApiListResponse<EmployeeDto>> deleteEmployee(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(employeeServiceI.deleteEmployee(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/employee/getClass/{employeeId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getEmployeesForClass(@PathVariable long employeeId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

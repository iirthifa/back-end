package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.DepartmentDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.DepartmentServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    private final DepartmentServiceI departmentServiceI;
    private final StatusServiceI statusServiceI;

    public DepartmentController(DepartmentServiceI departmentServiceI, StatusServiceI statusServiceI) {
        this.departmentServiceI = departmentServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("department/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/department")
    public ResponseEntity<ApiListResponse<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(ApiListResponse.of(departmentServiceI.getAllDepartments()));
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<ApiListResponse<DepartmentDto>> getDepartmentById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(departmentServiceI.getDepartmentById(id)));
    }

    @PostMapping("/department")
    public ResponseEntity<ApiListResponse<DepartmentDto>> addDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto created = departmentServiceI.addDepartment(departmentDto);
        return ResponseEntity.created(URI.create("/api/v1/department/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<ApiListResponse<DepartmentDto>> updateDepartment(
            @PathVariable long id,
            @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(departmentServiceI.updateDepartment(id, departmentDto)));
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<ApiListResponse<DepartmentDto>> deleteDepartment(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(departmentServiceI.deleteDepartment(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/department/getClass/{departmentId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getDepartmentsForClass(@PathVariable long departmentId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.GradeDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.GradeServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class GradeController {

    private final GradeServiceI gradeServiceI;
    private final StatusServiceI statusServiceI;

    public GradeController(GradeServiceI gradeServiceI, StatusServiceI statusServiceI) {
        this.gradeServiceI = gradeServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("grade/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/grade")
    public ResponseEntity<ApiListResponse<GradeDto>> getAllGrades() {
        return ResponseEntity.ok(ApiListResponse.of(gradeServiceI.getAllGrades()));
    }

    @GetMapping("/grade/{id}")
    public ResponseEntity<ApiListResponse<GradeDto>> getGradeById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(gradeServiceI.getGradeById(id)));
    }

    @PostMapping("/grade")
    public ResponseEntity<ApiListResponse<GradeDto>> addGrade(@RequestBody GradeDto gradeDto) {
        GradeDto created = gradeServiceI.addGrade(gradeDto);
        return ResponseEntity.created(URI.create("/api/v1/grade/" + created.getGrade_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/grade/{id}")
    public ResponseEntity<ApiListResponse<GradeDto>> updateGrade(
            @PathVariable long id,
            @RequestBody GradeDto gradeDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(gradeServiceI.updateGrade(id, gradeDto)));
    }

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<ApiListResponse<GradeDto>> deleteGrade(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(gradeServiceI.deleteGrade(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/grade/getClass/{gradeId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getGradesForClass(@PathVariable long gradeId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

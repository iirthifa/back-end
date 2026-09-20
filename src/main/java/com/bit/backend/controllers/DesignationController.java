package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.DesignationDto;
import com.bit.backend.services.DesignationServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DesignationController {

    private final DesignationServiceI designationServiceI;

    public DesignationController(DesignationServiceI designationServiceI) {
        this.designationServiceI = designationServiceI;
    }

    @GetMapping("/designation")
    public ResponseEntity<ApiListResponse<DesignationDto>> getAllDesignations() {
        return ResponseEntity.ok(ApiListResponse.of(designationServiceI.getAllDesignations()));
    }

    @GetMapping("/designation/{id}")
    public ResponseEntity<ApiListResponse<DesignationDto>> getDesignationById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(designationServiceI.getDesignationById(id)));
    }

    @PostMapping("/designation")
    public ResponseEntity<ApiListResponse<DesignationDto>> addDesignation(@RequestBody DesignationDto designationDto) {
        DesignationDto created = designationServiceI.addDesignation(designationDto);
        return ResponseEntity.created(URI.create("/api/v1/designation/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/designation/{id}")
    public ResponseEntity<ApiListResponse<DesignationDto>> updateDesignation(
            @PathVariable long id,
            @RequestBody DesignationDto designationDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(designationServiceI.updateDesignation(id, designationDto)));
    }

    @DeleteMapping("/designation/{id}")
    public ResponseEntity<ApiListResponse<DesignationDto>> deleteDesignation(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(designationServiceI.deleteDesignation(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/designation/getClass/{designationId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getDesignationsForClass(@PathVariable long designationId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

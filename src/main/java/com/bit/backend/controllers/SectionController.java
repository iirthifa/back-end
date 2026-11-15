package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.SectionDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.SectionServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SectionController {

    private final SectionServiceI sectionServiceI;
    private final StatusServiceI statusServiceI;

    public SectionController(SectionServiceI sectionServiceI, StatusServiceI statusServiceI) {
        this.sectionServiceI = sectionServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("section/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/section")
    public ResponseEntity<ApiListResponse<SectionDto>> getAllSections() {
        return ResponseEntity.ok(ApiListResponse.of(sectionServiceI.getAllSections()));
    }

    @GetMapping("/section/{id}")
    public ResponseEntity<ApiListResponse<SectionDto>> getSectionById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(sectionServiceI.getSectionById(id)));
    }

    @PostMapping("/section")
    public ResponseEntity<ApiListResponse<SectionDto>> addSection(@RequestBody SectionDto sectionDto) {
        SectionDto created = sectionServiceI.addSection(sectionDto);
        return ResponseEntity.created(URI.create("/api/v1/section/" + created.getSect_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/section/{id}")
    public ResponseEntity<ApiListResponse<SectionDto>> updateSection(
            @PathVariable long id,
            @RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(sectionServiceI.updateSection(id, sectionDto)));
    }

    @DeleteMapping("/section/{id}")
    public ResponseEntity<ApiListResponse<SectionDto>> deleteSection(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(sectionServiceI.deleteSection(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/section/getClass/{sectionId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getSectionsForClass(@PathVariable long sectionId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

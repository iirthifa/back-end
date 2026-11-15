package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.NationalityDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.NationalityServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class NationalityController {

    private final NationalityServiceI nationalityServiceI;
    private final StatusServiceI statusServiceI;

    public NationalityController(NationalityServiceI nationalityServiceI, StatusServiceI statusServiceI) {
        this.nationalityServiceI = nationalityServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("nationality/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/nationality")
    public ResponseEntity<ApiListResponse<NationalityDto>> getAllNationalitys() {
        return ResponseEntity.ok(ApiListResponse.of(nationalityServiceI.getAllNationalitys()));
    }

    @GetMapping("/nationality/{id}")
    public ResponseEntity<ApiListResponse<NationalityDto>> getNationalityById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(nationalityServiceI.getNationalityById(id)));
    }

    @PostMapping("/nationality")
    public ResponseEntity<ApiListResponse<NationalityDto>> addNationality(@RequestBody NationalityDto nationalityDto) {
        NationalityDto created = nationalityServiceI.addNationality(nationalityDto);
        return ResponseEntity.created(URI.create("/api/v1/nationality/" + created.getNationality_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/nationality/{id}")
    public ResponseEntity<ApiListResponse<NationalityDto>> updateNationality(
            @PathVariable long id,
            @RequestBody NationalityDto nationalityDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(nationalityServiceI.updateNationality(id, nationalityDto)));
    }

    @DeleteMapping("/nationality/{id}")
    public ResponseEntity<ApiListResponse<NationalityDto>> deleteNationality(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(nationalityServiceI.deleteNationality(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/nationality/getClass/{nationalityId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getNationalitysForClass(@PathVariable long nationalityId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

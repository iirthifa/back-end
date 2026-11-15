package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.DivisionalSecretariatDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.DivisionalSecretariatServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DivisionalSecretariatController {

    private final DivisionalSecretariatServiceI divisionalSecretariatServiceI;
    private final StatusServiceI statusServiceI;

    public DivisionalSecretariatController(DivisionalSecretariatServiceI divisionalSecretariatServiceI, StatusServiceI statusServiceI) {
        this.divisionalSecretariatServiceI = divisionalSecretariatServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("divisionalSecretariat/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/divisionalSecretariat")
    public ResponseEntity<ApiListResponse<DivisionalSecretariatDto>> getAllDivisionalSecretariats() {
        return ResponseEntity.ok(ApiListResponse.of(divisionalSecretariatServiceI.getAllDivisionalSecretariats()));
    }

    @GetMapping("/divisionalSecretariat/{id}")
    public ResponseEntity<ApiListResponse<DivisionalSecretariatDto>> getDivisionalSecretariatById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(divisionalSecretariatServiceI.getDivisionalSecretariatById(id)));
    }

    @PostMapping("/divisionalSecretariat")
    public ResponseEntity<ApiListResponse<DivisionalSecretariatDto>> addDivisionalSecretariat(@RequestBody DivisionalSecretariatDto divisionalSecretariatDto) {
        DivisionalSecretariatDto created = divisionalSecretariatServiceI.addDivisionalSecretariat(divisionalSecretariatDto);
        return ResponseEntity.created(URI.create("/api/v1/divisionalSecretariat/" + created.getDev_sect_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/divisionalSecretariat/{id}")
    public ResponseEntity<ApiListResponse<DivisionalSecretariatDto>> updateDivisionalSecretariat(
            @PathVariable long id,
            @RequestBody DivisionalSecretariatDto divisionalSecretariatDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(divisionalSecretariatServiceI.updateDivisionalSecretariat(id, divisionalSecretariatDto)));
    }

    @DeleteMapping("/divisionalSecretariat/{id}")
    public ResponseEntity<ApiListResponse<DivisionalSecretariatDto>> deleteDivisionalSecretariat(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(divisionalSecretariatServiceI.deleteDivisionalSecretariat(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/divisionalSecretariat/getClass/{divisionalSecretariatId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getDivisionalSecretariatsForClass(@PathVariable long divisionalSecretariatId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

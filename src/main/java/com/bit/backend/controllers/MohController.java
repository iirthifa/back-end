package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.MohDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.MohServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MohController {

    private final MohServiceI mohServiceI;
    private final StatusServiceI statusServiceI;

    public MohController(MohServiceI mohServiceI, StatusServiceI statusServiceI) {
        this.mohServiceI = mohServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("moh/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/moh")
    public ResponseEntity<ApiListResponse<MohDto>> getAllMohs() {
        return ResponseEntity.ok(ApiListResponse.of(mohServiceI.getAllMohs()));
    }

    @GetMapping("/moh/{id}")
    public ResponseEntity<ApiListResponse<MohDto>> getMohById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(mohServiceI.getMohById(id)));
    }

    @PostMapping("/moh")
    public ResponseEntity<ApiListResponse<MohDto>> addMoh(@RequestBody MohDto mohDto) {
        MohDto created = mohServiceI.addMoh(mohDto);
        return ResponseEntity.created(URI.create("/api/v1/moh/" + created.getMoh_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/moh/{id}")
    public ResponseEntity<ApiListResponse<MohDto>> updateMoh(
            @PathVariable long id,
            @RequestBody MohDto mohDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(mohServiceI.updateMoh(id, mohDto)));
    }

    @DeleteMapping("/moh/{id}")
    public ResponseEntity<ApiListResponse<MohDto>> deleteMoh(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(mohServiceI.deleteMoh(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/moh/getClass/{mohId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getMohsForClass(@PathVariable long mohId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

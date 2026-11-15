package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.ProvinceDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.ProvinceServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProvinceController {

    private final ProvinceServiceI provinceServiceI;
    private final StatusServiceI statusServiceI;

    public ProvinceController(ProvinceServiceI provinceServiceI, StatusServiceI statusServiceI) {
        this.provinceServiceI = provinceServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("province/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/province")
    public ResponseEntity<ApiListResponse<ProvinceDto>> getAllProvinces() {
        return ResponseEntity.ok(ApiListResponse.of(provinceServiceI.getAllProvinces()));
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<ApiListResponse<ProvinceDto>> getProvinceById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(provinceServiceI.getProvinceById(id)));
    }

    @PostMapping("/province")
    public ResponseEntity<ApiListResponse<ProvinceDto>> addProvince(@RequestBody ProvinceDto provinceDto) {
        ProvinceDto created = provinceServiceI.addProvince(provinceDto);
        return ResponseEntity.created(URI.create("/api/v1/province/" + created.getProvince_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/province/{id}")
    public ResponseEntity<ApiListResponse<ProvinceDto>> updateProvince(
            @PathVariable long id,
            @RequestBody ProvinceDto provinceDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(provinceServiceI.updateProvince(id, provinceDto)));
    }

    @DeleteMapping("/province/{id}")
    public ResponseEntity<ApiListResponse<ProvinceDto>> deleteProvince(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(provinceServiceI.deleteProvince(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/province/getClass/{provinceId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getProvincesForClass(@PathVariable long provinceId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

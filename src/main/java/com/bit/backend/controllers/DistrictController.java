package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.DistrictDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.DistrictServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DistrictController {

    private final DistrictServiceI districtServiceI;
    private final StatusServiceI statusServiceI;

    public DistrictController(DistrictServiceI districtServiceI, StatusServiceI statusServiceI) {
        this.districtServiceI = districtServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("district/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/district")
    public ResponseEntity<ApiListResponse<DistrictDto>> getAllDistricts() {
        return ResponseEntity.ok(ApiListResponse.of(districtServiceI.getAllDistricts()));
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<ApiListResponse<DistrictDto>> getDistrictById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(districtServiceI.getDistrictById(id)));
    }

    @PostMapping("/district")
    public ResponseEntity<ApiListResponse<DistrictDto>> addDistrict(@RequestBody DistrictDto districtDto) {
        DistrictDto created = districtServiceI.addDistrict(districtDto);
        return ResponseEntity.created(URI.create("/api/v1/district/" + created.getDistrict_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/district/{id}")
    public ResponseEntity<ApiListResponse<DistrictDto>> updateDistrict(
            @PathVariable long id,
            @RequestBody DistrictDto districtDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(districtServiceI.updateDistrict(id, districtDto)));
    }

    @DeleteMapping("/district/{id}")
    public ResponseEntity<ApiListResponse<DistrictDto>> deleteDistrict(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(districtServiceI.deleteDistrict(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/district/getClass/{districtId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getDistrictsForClass(@PathVariable long districtId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

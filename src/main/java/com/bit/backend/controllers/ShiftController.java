package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.ShiftDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.ShiftServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ShiftController {

    private final ShiftServiceI shiftServiceI;
    private final StatusServiceI statusServiceI;

    public ShiftController(ShiftServiceI shiftServiceI, StatusServiceI statusServiceI) {
        this.shiftServiceI = shiftServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("shift/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/shift")
    public ResponseEntity<ApiListResponse<ShiftDto>> getAllShifts() {
        return ResponseEntity.ok(ApiListResponse.of(shiftServiceI.getAllShifts()));
    }

    @GetMapping("/shift/{id}")
    public ResponseEntity<ApiListResponse<ShiftDto>> getShiftById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(shiftServiceI.getShiftById(id)));
    }

    @PostMapping("/shift")
    public ResponseEntity<ApiListResponse<ShiftDto>> addShift(@RequestBody ShiftDto shiftDto) {
        ShiftDto created = shiftServiceI.addShift(shiftDto);
        return ResponseEntity.created(URI.create("/api/v1/shift/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/shift/{id}")
    public ResponseEntity<ApiListResponse<ShiftDto>> updateShift(
            @PathVariable long id,
            @RequestBody ShiftDto shiftDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(shiftServiceI.updateShift(id, shiftDto)));
    }

    @DeleteMapping("/shift/{id}")
    public ResponseEntity<ApiListResponse<ShiftDto>> deleteShift(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(shiftServiceI.deleteShift(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/shift/getClass/{shiftId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getShiftsForClass(@PathVariable long shiftId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

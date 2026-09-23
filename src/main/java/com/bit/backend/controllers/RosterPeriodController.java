package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.RosterPeriodDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.RosterPeriodServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RosterPeriodController {

    private final RosterPeriodServiceI rosterPeriodServiceI;
    private final StatusServiceI statusServiceI;

    public RosterPeriodController(RosterPeriodServiceI rosterPeriodServiceI, StatusServiceI statusServiceI) {
        this.rosterPeriodServiceI = rosterPeriodServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("rosterPeriod/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/rosterPeriod")
    public ResponseEntity<ApiListResponse<RosterPeriodDto>> getAllRosterPeriods() {
        return ResponseEntity.ok(ApiListResponse.of(rosterPeriodServiceI.getAllRosterPeriods()));
    }

    @GetMapping("/rosterPeriod/{id}")
    public ResponseEntity<ApiListResponse<RosterPeriodDto>> getRosterPeriodById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(rosterPeriodServiceI.getRosterPeriodById(id)));
    }

    @PostMapping("/rosterPeriod")
    public ResponseEntity<ApiListResponse<RosterPeriodDto>> addRosterPeriod(@RequestBody RosterPeriodDto rosterPeriodDto) {
        RosterPeriodDto created = rosterPeriodServiceI.addRosterPeriod(rosterPeriodDto);
        return ResponseEntity.created(URI.create("/api/v1/rosterPeriod/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/rosterPeriod/{id}")
    public ResponseEntity<ApiListResponse<RosterPeriodDto>> updateRosterPeriod(
            @PathVariable long id,
            @RequestBody RosterPeriodDto rosterPeriodDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(rosterPeriodServiceI.updateRosterPeriod(id, rosterPeriodDto)));
    }

    @DeleteMapping("/rosterPeriod/{id}")
    public ResponseEntity<ApiListResponse<RosterPeriodDto>> deleteRosterPeriod(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(rosterPeriodServiceI.deleteRosterPeriod(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/rosterPeriod/getClass/{rosterPeriodId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getRosterPeriodsForClass(@PathVariable long rosterPeriodId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

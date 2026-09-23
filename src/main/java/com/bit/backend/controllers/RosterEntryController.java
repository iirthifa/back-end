package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.RosterEntryDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.RosterEntryServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RosterEntryController {

    private final RosterEntryServiceI rosterEntryServiceI;
    private final StatusServiceI statusServiceI;

    public RosterEntryController(RosterEntryServiceI rosterEntryServiceI, StatusServiceI statusServiceI) {
        this.rosterEntryServiceI = rosterEntryServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("rosterEntry/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/rosterEntry")
    public ResponseEntity<ApiListResponse<RosterEntryDto>> getAllRosterEntrys() {
        return ResponseEntity.ok(ApiListResponse.of(rosterEntryServiceI.getAllRosterEntries()));
    }

    @GetMapping("/rosterEntry/{id}")
    public ResponseEntity<ApiListResponse<RosterEntryDto>> getRosterEntryById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(rosterEntryServiceI.getRosterEntryById(id)));
    }

    @PostMapping("/rosterEntry")
    public ResponseEntity<ApiListResponse<RosterEntryDto>> addRosterEntry(@RequestBody RosterEntryDto rosterEntryDto) {
        RosterEntryDto created = rosterEntryServiceI.addRosterEntry(rosterEntryDto);
        return ResponseEntity.created(URI.create("/api/v1/rosterEntry/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/rosterEntry/{id}")
    public ResponseEntity<ApiListResponse<RosterEntryDto>> updateRosterEntry(
            @PathVariable long id,
            @RequestBody RosterEntryDto rosterEntryDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(rosterEntryServiceI.updateRosterEntry(id, rosterEntryDto)));
    }

    @DeleteMapping("/rosterEntry/{id}")
    public ResponseEntity<ApiListResponse<RosterEntryDto>> deleteRosterEntry(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(rosterEntryServiceI.deleteRosterEntry(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/rosterEntry/getClass/{rosterEntryId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getRosterEntriesForClass(@PathVariable long rosterEntryId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.GnDivisionDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.GnDivisionServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class GnDivisionController {

    private final GnDivisionServiceI gnDivisionServiceI;
    private final StatusServiceI statusServiceI;

    public GnDivisionController(GnDivisionServiceI gnDivisionServiceI, StatusServiceI statusServiceI) {
        this.gnDivisionServiceI = gnDivisionServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("gnDivision/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/gnDivision")
    public ResponseEntity<ApiListResponse<GnDivisionDto>> getAllGnDivisions() {
        return ResponseEntity.ok(ApiListResponse.of(gnDivisionServiceI.getAllGnDivisions()));
    }

    @GetMapping("/gnDivision/{id}")
    public ResponseEntity<ApiListResponse<GnDivisionDto>> getGnDivisionById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(gnDivisionServiceI.getGnDivisionById(id)));
    }

    @PostMapping("/gnDivision")
    public ResponseEntity<ApiListResponse<GnDivisionDto>> addGnDivision(@RequestBody GnDivisionDto gnDivisionDto) {
        GnDivisionDto created = gnDivisionServiceI.addGnDivision(gnDivisionDto);
        return ResponseEntity.created(URI.create("/api/v1/gnDivision/" + created.getGnd_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/gnDivision/{id}")
    public ResponseEntity<ApiListResponse<GnDivisionDto>> updateGnDivision(
            @PathVariable long id,
            @RequestBody GnDivisionDto gnDivisionDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(gnDivisionServiceI.updateGnDivision(id, gnDivisionDto)));
    }

    @DeleteMapping("/gnDivision/{id}")
    public ResponseEntity<ApiListResponse<GnDivisionDto>> deleteGnDivision(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(gnDivisionServiceI.deleteGnDivision(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/gnDivision/getClass/{gnDivisionId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getGnDivisionsForClass(@PathVariable long gnDivisionId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

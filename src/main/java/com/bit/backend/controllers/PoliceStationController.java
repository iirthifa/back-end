package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.PoliceStationDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.PoliceStationServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PoliceStationController {

    private final PoliceStationServiceI policeStationServiceI;
    private final StatusServiceI statusServiceI;

    public PoliceStationController(PoliceStationServiceI policeStationServiceI, StatusServiceI statusServiceI) {
        this.policeStationServiceI = policeStationServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("policeStation/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/policeStation")
    public ResponseEntity<ApiListResponse<PoliceStationDto>> getAllPoliceStations() {
        return ResponseEntity.ok(ApiListResponse.of(policeStationServiceI.getAllPoliceStations()));
    }

    @GetMapping("/policeStation/{id}")
    public ResponseEntity<ApiListResponse<PoliceStationDto>> getPoliceStationById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(policeStationServiceI.getPoliceStationById(id)));
    }

    @PostMapping("/policeStation")
    public ResponseEntity<ApiListResponse<PoliceStationDto>> addPoliceStation(@RequestBody PoliceStationDto policeStationDto) {
        PoliceStationDto created = policeStationServiceI.addPoliceStation(policeStationDto);
        return ResponseEntity.created(URI.create("/api/v1/policeStation/" + created.getPolice_station_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/policeStation/{id}")
    public ResponseEntity<ApiListResponse<PoliceStationDto>> updatePoliceStation(
            @PathVariable long id,
            @RequestBody PoliceStationDto policeStationDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(policeStationServiceI.updatePoliceStation(id, policeStationDto)));
    }

    @DeleteMapping("/policeStation/{id}")
    public ResponseEntity<ApiListResponse<PoliceStationDto>> deletePoliceStation(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(policeStationServiceI.deletePoliceStation(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/policeStation/getClass/{policeStationId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getPoliceStationsForClass(@PathVariable long policeStationId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.PollingDevisionDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.PollingDevisionServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PollingDevisionController {

    private final PollingDevisionServiceI pollingDevisionServiceI;
    private final StatusServiceI statusServiceI;

    public PollingDevisionController(PollingDevisionServiceI pollingDevisionServiceI, StatusServiceI statusServiceI) {
        this.pollingDevisionServiceI = pollingDevisionServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("pollingDevision/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/pollingDevision")
    public ResponseEntity<ApiListResponse<PollingDevisionDto>> getAllPollingDevisions() {
        return ResponseEntity.ok(ApiListResponse.of(pollingDevisionServiceI.getAllPollingDevisions()));
    }

    @GetMapping("/pollingDevision/{id}")
    public ResponseEntity<ApiListResponse<PollingDevisionDto>> getPollingDevisionById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(pollingDevisionServiceI.getPollingDevisionById(id)));
    }

    @PostMapping("/pollingDevision")
    public ResponseEntity<ApiListResponse<PollingDevisionDto>> addPollingDevision(@RequestBody PollingDevisionDto pollingDevisionDto) {
        PollingDevisionDto created = pollingDevisionServiceI.addPollingDevision(pollingDevisionDto);
        return ResponseEntity.created(URI.create("/api/v1/pollingDevision/" + created.getPolling_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/pollingDevision/{id}")
    public ResponseEntity<ApiListResponse<PollingDevisionDto>> updatePollingDevision(
            @PathVariable long id,
            @RequestBody PollingDevisionDto pollingDevisionDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(pollingDevisionServiceI.updatePollingDevision(id, pollingDevisionDto)));
    }

    @DeleteMapping("/pollingDevision/{id}")
    public ResponseEntity<ApiListResponse<PollingDevisionDto>> deletePollingDevision(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(pollingDevisionServiceI.deletePollingDevision(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/pollingDevision/getClass/{pollingDevisionId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getPollingDevisionsForClass(@PathVariable long pollingDevisionId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

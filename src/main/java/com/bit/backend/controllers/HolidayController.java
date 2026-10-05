package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.HolidayDto;
import com.bit.backend.services.HolidayServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HolidayController {

    private final HolidayServiceI holidayServiceI;

    public HolidayController(HolidayServiceI holidayServiceI) {
        this.holidayServiceI = holidayServiceI;
    }

    @GetMapping("/holiday")
    public ResponseEntity<ApiListResponse<HolidayDto>> getAllHolidays() {
        return ResponseEntity.ok(ApiListResponse.of(holidayServiceI.getAllHolidays()));
    }

    @GetMapping("/holiday/{id}")
    public ResponseEntity<ApiListResponse<HolidayDto>> getHolidayById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(holidayServiceI.getHolidayById(id)));
    }

    @PostMapping("/holiday")
    public ResponseEntity<ApiListResponse<HolidayDto>> addHoliday(@RequestBody HolidayDto holidayDto) {
        HolidayDto created = holidayServiceI.addHoliday(holidayDto);
        return ResponseEntity.created(URI.create("/api/v1/holiday/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/holiday/{id}")
    public ResponseEntity<ApiListResponse<HolidayDto>> updateHoliday(
            @PathVariable long id,
            @RequestBody HolidayDto holidayDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(holidayServiceI.updateHoliday(id, holidayDto)));
    }

    @DeleteMapping("/holiday/{id}")
    public ResponseEntity<ApiListResponse<HolidayDto>> deleteHoliday(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(holidayServiceI.deleteHoliday(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.

    @GetMapping("/holiday/getClass/{courseId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getHolidaysForClass(@PathVariable long courseId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }*/
}

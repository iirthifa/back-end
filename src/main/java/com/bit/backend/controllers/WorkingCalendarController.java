package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.WorkingCalendarDto;
import com.bit.backend.services.WorkingCalendarServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class WorkingCalendarController {

    private final WorkingCalendarServiceI workingCalendarServiceI;

    public WorkingCalendarController(WorkingCalendarServiceI workingCalendarServiceI) {
        this.workingCalendarServiceI = workingCalendarServiceI;
    }

    @GetMapping("/workingCalendar")
    public ResponseEntity<ApiListResponse<WorkingCalendarDto>> getAllWorkingCalendars() {
        return ResponseEntity.ok(ApiListResponse.of(workingCalendarServiceI.getAllWorkingCalendars()));
    }

    @GetMapping("/workingCalendar/{id}")
    public ResponseEntity<ApiListResponse<WorkingCalendarDto>> getWorkingCalendarById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(workingCalendarServiceI.getWorkingCalendarById(id)));
    }

    @PostMapping("/workingCalendar")
    public ResponseEntity<ApiListResponse<WorkingCalendarDto>> addWorkingCalendar(@RequestBody WorkingCalendarDto workingCalendarDto) {
        WorkingCalendarDto created = workingCalendarServiceI.addWorkingCalendar(workingCalendarDto);
        return ResponseEntity.created(URI.create("/api/v1/workingCalendar/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/workingCalendar/{id}")
    public ResponseEntity<ApiListResponse<WorkingCalendarDto>> updateWorkingCalendar(
            @PathVariable long id,
            @RequestBody WorkingCalendarDto workingCalendarDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(workingCalendarServiceI.updateWorkingCalendar(id, workingCalendarDto)));
    }

    @DeleteMapping("/workingCalendar/{id}")
    public ResponseEntity<ApiListResponse<WorkingCalendarDto>> deleteWorkingCalendar(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(workingCalendarServiceI.deleteWorkingCalendar(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.

    @GetMapping("/workingCalendar/getClass/{courseId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getWorkingCalendarsForClass(@PathVariable long workingCalendarId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }*/
}

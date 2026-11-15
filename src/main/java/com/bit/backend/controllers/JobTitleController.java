package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.JobTitleDto;
import com.bit.backend.services.StatusServiceI;
import com.bit.backend.services.JobTitleServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class JobTitleController {

    private final JobTitleServiceI jobTitleServiceI;
    private final StatusServiceI statusServiceI;

    public JobTitleController(JobTitleServiceI jobTitleServiceI, StatusServiceI statusServiceI) {
        this.jobTitleServiceI = jobTitleServiceI;
        this.statusServiceI = statusServiceI;
    }

    @GetMapping("jobTitle/status")
    public ResponseEntity<ApiListResponse<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(ApiListResponse.of(statusServiceI.getAllStatus()));
    }

    @GetMapping("/jobTitle")
    public ResponseEntity<ApiListResponse<JobTitleDto>> getAllJobTitles() {
        return ResponseEntity.ok(ApiListResponse.of(jobTitleServiceI.getAllJobTitles()));
    }

    @GetMapping("/jobTitle/{id}")
    public ResponseEntity<ApiListResponse<JobTitleDto>> getJobTitleById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(jobTitleServiceI.getJobTitleById(id)));
    }

    @PostMapping("/jobTitle")
    public ResponseEntity<ApiListResponse<JobTitleDto>> addJobTitle(@RequestBody JobTitleDto jobTitleDto) {
        JobTitleDto created = jobTitleServiceI.addJobTitle(jobTitleDto);
        return ResponseEntity.created(URI.create("/api/v1/jobTitle/" + created.getJt_serial_id()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/jobTitle/{id}")
    public ResponseEntity<ApiListResponse<JobTitleDto>> updateJobTitle(
            @PathVariable long id,
            @RequestBody JobTitleDto jobTitleDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(jobTitleServiceI.updateJobTitle(id, jobTitleDto)));
    }

    @DeleteMapping("/jobTitle/{id}")
    public ResponseEntity<ApiListResponse<JobTitleDto>> deleteJobTitle(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(jobTitleServiceI.deleteJobTitle(id)));
    }

    /**
     * Used by Class Registration grid filter.
     * Returns empty list until course-enrollment APIs are added.
     */
    @GetMapping("/jobTitle/getClass/{jobTitleId}")
    public ResponseEntity<ApiListResponse<Map<String, Object>>> getJobTitlesForClass(@PathVariable long jobTitleId) {
        List<Map<String, Object>> empty = Collections.emptyList();
        return ResponseEntity.ok(ApiListResponse.of(empty));
    }
}

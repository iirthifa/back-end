package com.bit.backend.services;

import com.bit.backend.dtos.JobTitleDto;

import java.util.List;

public interface JobTitleServiceI {
    JobTitleDto addJobTitle(JobTitleDto jobTitleDto);
    List<JobTitleDto> getAllJobTitles();
    JobTitleDto getJobTitleById(long id);
    JobTitleDto updateJobTitle(long id, JobTitleDto jobTitleDto);
    JobTitleDto deleteJobTitle(long id);
}

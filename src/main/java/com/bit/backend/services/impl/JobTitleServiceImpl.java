package com.bit.backend.services.impl;

import com.bit.backend.dtos.JobTitleDto;
import com.bit.backend.entities.JobTitleEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.JobTitleMapper;
import com.bit.backend.repositories.JobTitleRepository;
import com.bit.backend.services.JobTitleServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class JobTitleServiceImpl implements JobTitleServiceI {

    private final JobTitleRepository jobTitleRepository;
    private final JobTitleMapper jobTitleMapper;

    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository, JobTitleMapper jobTitleMapper) {
        this.jobTitleRepository = jobTitleRepository;
        this.jobTitleMapper = jobTitleMapper;
    }

    @Override
    @Transactional
    public JobTitleDto addJobTitle(JobTitleDto jobTitleDto) {
        JobTitleEntity entity = jobTitleMapper.toJobTitleEntity(jobTitleDto);

        entity.setJt_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return jobTitleMapper.toJobTitleDto(jobTitleRepository.save(entity));
    }

    @Override
    public List<JobTitleDto> getAllJobTitles() {
        return jobTitleMapper.toJobTitleDtoList(jobTitleRepository.findAll());
    }

    @Override
    public JobTitleDto getJobTitleById(long id) {
        JobTitleEntity entity = jobTitleRepository.findById(id)
                .orElseThrow(() -> new AppException("JobTitle not found", HttpStatus.NOT_FOUND));
        return jobTitleMapper.toJobTitleDto(entity);
    }

    @Override
    @Transactional
    public JobTitleDto updateJobTitle(long id, JobTitleDto jobTitleDto) {
        JobTitleEntity existing = jobTitleRepository.findById(id)
                .orElseThrow(() -> new AppException("JobTitle not found", HttpStatus.NOT_FOUND));

        setIfPresent(jobTitleDto.getJobTitleName(), existing::setJobTitleName);
        setIfPresent(jobTitleDto.getActive(), existing::setActive);

        return jobTitleMapper.toJobTitleDto(jobTitleRepository.save(existing));
    }

    @Override
    @Transactional
    public JobTitleDto deleteJobTitle(long id) {
        JobTitleEntity existing = jobTitleRepository.findById(id)
                .orElseThrow(() -> new AppException("JobTitle not found", HttpStatus.NOT_FOUND));
        JobTitleDto dto = jobTitleMapper.toJobTitleDto(existing);
        jobTitleRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

package com.bit.backend.mappers;

import com.bit.backend.dtos.JobTitleDto;
import com.bit.backend.entities.JobTitleEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface JobTitleMapper {
    JobTitleDto toJobTitleDto(JobTitleEntity entity);

    List<JobTitleDto> toJobTitleDtoList(List<JobTitleEntity> entities);

    JobTitleEntity toJobTitleEntity(JobTitleDto dto);
}

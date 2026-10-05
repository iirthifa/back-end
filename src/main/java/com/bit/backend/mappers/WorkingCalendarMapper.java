package com.bit.backend.mappers;

import com.bit.backend.dtos.WorkingCalendarDto;
import com.bit.backend.entities.WorkingCalendarEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface WorkingCalendarMapper {

    WorkingCalendarDto toWorkingCalendarDto(WorkingCalendarEntity entity);

    List<WorkingCalendarDto> toWorkingCalendarDtoList(List<WorkingCalendarEntity> entities);

    WorkingCalendarEntity toWorkingCalendarEntity(WorkingCalendarDto dto);
}

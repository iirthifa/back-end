package com.bit.backend.mappers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.ShiftDto;
import com.bit.backend.dtos.RosterPeriodDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.RosterEntryDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.ShiftEntity;
import com.bit.backend.entities.RosterPeriodEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.RosterEntryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RosterEntryMapper {

    EmployeeDto toEmployeeDto(EmployeeEntity entity);

    List<EmployeeDto> toEmployeeDtoList(List<EmployeeEntity> entities);

    ShiftDto toShiftDto(ShiftEntity entity);

    List<ShiftDto> toShiftDtoList(List<ShiftEntity> entities);

    RosterPeriodDto toRosterPeriodDto(RosterPeriodEntity entity);

    List<RosterPeriodDto> toRosterPeriodDtoList(List<RosterPeriodEntity> entities);

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "employee", source = "employee")
    @Mapping(target = "shift", source = "shift")
    @Mapping(target = "rosterPeriod", source = "rosterPeriod")
    @Mapping(target = "status", source = "status")
    RosterEntryDto toRosterEntryDto(RosterEntryEntity entity);

    List<RosterEntryDto> toRosterEntryDtoList(List<RosterEntryEntity> entities);

    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "shift", ignore = true)
    @Mapping(target = "rosterPeriod", ignore = true)
    @Mapping(target = "status", ignore = true)
    RosterEntryEntity toRosterEntryEntity(RosterEntryDto dto);
}

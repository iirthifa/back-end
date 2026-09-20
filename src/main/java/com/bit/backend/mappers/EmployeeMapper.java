package com.bit.backend.mappers;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.EmployeeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EmployeeMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    EmployeeDto toEmployeeDto(EmployeeEntity entity);

    List<EmployeeDto> toEmployeeDtoList(List<EmployeeEntity> entities);

    @Mapping(target = "status", ignore = true)
    EmployeeEntity toEmployeeEntity(EmployeeDto dto);
}

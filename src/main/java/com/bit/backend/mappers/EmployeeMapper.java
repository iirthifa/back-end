package com.bit.backend.mappers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.EmployeeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(EmployeeEntity entity);

    List<EmployeeDto> toEmployeeDtoList(List<EmployeeEntity> entities);

    @Mapping(target = "eeid", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    EmployeeEntity toEmployeeEntity(EmployeeDto dto);
}

package com.bit.backend.mappers;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.DepartmentDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.DepartmentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DepartmentMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    DepartmentDto toDepartmentDto(DepartmentEntity entity);

    List<DepartmentDto> toDepartmentDtoList(List<DepartmentEntity> entities);

    @Mapping(target = "status", ignore = true)
    DepartmentEntity toDepartmentEntity(DepartmentDto dto);
}

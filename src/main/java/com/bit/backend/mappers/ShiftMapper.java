package com.bit.backend.mappers;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.ShiftDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.ShiftEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ShiftMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    ShiftDto toShiftDto(ShiftEntity entity);

    List<ShiftDto> toShiftDtoList(List<ShiftEntity> entities);

    @Mapping(target = "status", ignore = true)
    ShiftEntity toShiftEntity(ShiftDto dto);
}

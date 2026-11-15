package com.bit.backend.mappers;

import com.bit.backend.dtos.GnDivisionDto;
import com.bit.backend.entities.GnDivisionEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface GnDivisionMapper {
    GnDivisionDto toGnDivisionDto(GnDivisionEntity entity);

    List<GnDivisionDto> toGnDivisionDtoList(List<GnDivisionEntity> entities);

    GnDivisionEntity toGnDivisionEntity(GnDivisionDto dto);
}
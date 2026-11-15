package com.bit.backend.mappers;

import com.bit.backend.dtos.DistrictDto;
import com.bit.backend.entities.DistrictEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DistrictMapper {
    DistrictDto toDistrictDto(DistrictEntity entity);

    List<DistrictDto> toDistrictDtoList(List<DistrictEntity> entities);

    DistrictEntity toDistrictEntity(DistrictDto dto);
}

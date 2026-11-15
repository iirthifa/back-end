package com.bit.backend.mappers;

import com.bit.backend.dtos.PoliceStationDto;
import com.bit.backend.entities.PoliceStationEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PoliceStationMapper {
    PoliceStationDto toPoliceStationDto(PoliceStationEntity entity);

    List<PoliceStationDto> toPoliceStationDtoList(List<PoliceStationEntity> entities);

    PoliceStationEntity toPoliceStationEntity(PoliceStationDto dto);
}

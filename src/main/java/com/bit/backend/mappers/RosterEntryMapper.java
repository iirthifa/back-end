package com.bit.backend.mappers;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.RosterEntryDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.RosterEntryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RosterEntryMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    RosterEntryDto toRosterEntryDto(RosterEntryEntity entity);

    List<RosterEntryDto> toRosterEntryDtoList(List<RosterEntryEntity> entities);

    @Mapping(target = "status", ignore = true)
    RosterEntryEntity toRosterEntryEntity(RosterEntryDto dto);
}

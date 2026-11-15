package com.bit.backend.mappers;

import com.bit.backend.dtos.SectionDto;
import com.bit.backend.entities.SectionEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SectionMapper {
    SectionDto toSectionDto(SectionEntity entity);

    List<SectionDto> toSectionDtoList(List<SectionEntity> entities);

    SectionEntity toSectionEntity(SectionDto dto);
}
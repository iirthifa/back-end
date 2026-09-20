package com.bit.backend.mappers;

import com.bit.backend.dtos.DesignationDto;
import com.bit.backend.entities.DesignationEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DesignationMapper {

    DesignationDto toDesignationDto(DesignationEntity entity);

    List<DesignationDto> toDesignationDtoList(List<DesignationEntity> entities);

    DesignationEntity toDesignationEntity(DesignationDto dto);
}
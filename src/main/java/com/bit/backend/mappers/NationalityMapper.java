package com.bit.backend.mappers;

import com.bit.backend.dtos.NationalityDto;
import com.bit.backend.entities.NationalityEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface NationalityMapper {
    NationalityDto toNationalityDto(NationalityEntity entity);

    List<NationalityDto> toNationalityDtoList(List<NationalityEntity> entities);

    NationalityEntity toNationalityEntity(NationalityDto dto);
}

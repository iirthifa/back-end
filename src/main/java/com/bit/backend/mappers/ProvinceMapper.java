package com.bit.backend.mappers;

import com.bit.backend.dtos.ProvinceDto;
import com.bit.backend.entities.ProvinceEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProvinceMapper {
    ProvinceDto toProvinceDto(ProvinceEntity entity);

    List<ProvinceDto> toProvinceDtoList(List<ProvinceEntity> entities);

    ProvinceEntity toProvinceEntity(ProvinceDto dto);
}
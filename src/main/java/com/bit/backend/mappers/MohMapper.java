package com.bit.backend.mappers;

import com.bit.backend.dtos.MohDto;
import com.bit.backend.entities.MohEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MohMapper {
    MohDto toMohDto(MohEntity entity);

    List<MohDto> toMohDtoList(List<MohEntity> entities);

    MohEntity toMohEntity(MohDto dto);
}

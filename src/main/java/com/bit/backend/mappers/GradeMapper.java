package com.bit.backend.mappers;

import com.bit.backend.dtos.GradeDto;
import com.bit.backend.entities.GradeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface GradeMapper {
    GradeDto toGradeDto(GradeEntity entity);

    List<GradeDto> toGradeDtoList(List<GradeEntity> entities);

    GradeEntity toGradeEntity(GradeDto dto);
}

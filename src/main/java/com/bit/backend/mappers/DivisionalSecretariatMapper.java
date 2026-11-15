package com.bit.backend.mappers;

import com.bit.backend.dtos.DivisionalSecretariatDto;
import com.bit.backend.entities.DivisionalSecretariatEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DivisionalSecretariatMapper {
    DivisionalSecretariatDto toDivisionalSecretariatDto(DivisionalSecretariatEntity entity);

    List<DivisionalSecretariatDto> toDivisionalSecretariatDtoList(List<DivisionalSecretariatEntity> entities);

    DivisionalSecretariatEntity toDivisionalSecretariatEntity(DivisionalSecretariatDto dto);
}

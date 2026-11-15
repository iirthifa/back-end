package com.bit.backend.mappers;

import com.bit.backend.dtos.PollingDevisionDto;
import com.bit.backend.entities.PollingDevisionEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PollingDevisionMapper {
    PollingDevisionDto toPollingDevisionDto(PollingDevisionEntity entity);

    List<PollingDevisionDto> toPollingDevisionDtoList(List<PollingDevisionEntity> entities);

    PollingDevisionEntity toPollingDevisionEntity(PollingDevisionDto dto);
}
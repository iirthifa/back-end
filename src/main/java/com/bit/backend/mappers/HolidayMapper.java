package com.bit.backend.mappers;

import com.bit.backend.dtos.HolidayDto;
import com.bit.backend.entities.HolidayEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface HolidayMapper {

    HolidayDto toHolidayDto(HolidayEntity entity);

    List<HolidayDto> toHolidayDtoList(List<HolidayEntity> entities);

    HolidayEntity toHolidayEntity(HolidayDto dto);
}

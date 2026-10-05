package com.bit.backend.services;

import com.bit.backend.dtos.HolidayDto;

import java.util.List;

public interface HolidayServiceI {
    HolidayDto addHoliday(HolidayDto studentDto);
    List<HolidayDto> getAllHolidays();
    HolidayDto getHolidayById(long id);
    HolidayDto updateHoliday(long id, HolidayDto holidayDto);
    HolidayDto deleteHoliday(long id);
}
